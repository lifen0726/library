package tw.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import tw.library.exception.UserNotFoundException;
import tw.library.model.User;
import tw.library.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findUserByName(@PathVariable String username) {
        try {
            User user = userService.findByName(username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(HttpServletRequest request) {
        try {
            String username = request.getParameter("username");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");

            if (password != null && !password.trim().isEmpty()) {
                if (!userService.existsByUsername(username)) {
                    if (!userService.existsByPhone(phone)) { // 加入手機號碼驗證邏輯
                        User user = new User();
                        user.setName(username);
                        user.setPhone(phone);

                        // 在設置之前對密碼進行編碼
                        String encodedPassword = passwordEncoder.encode(password);
                        user.setPassword(encodedPassword);
                        
                     // 設置註冊時間
                        user.setRegistrationTime(LocalDateTime.now());

                        userService.saveUser(user);
                        return ResponseEntity.ok("User registered successfully");
                    } else {
                        return new ResponseEntity<>("手機號碼已被使用", HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return new ResponseEntity<>("用戶名已被使用", HttpStatus.BAD_REQUEST);
                } 
            } else {
                return new ResponseEntity<>("密碼不能為空", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("註冊失敗", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        try {
            // 對密碼進行編碼
            String encodedPassword = passwordEncoder.encode(password);

            // 使用 AuthenticationManager 進行身份驗證
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, encodedPassword)
            );

            // 設定身份驗證結果到 Security 上下文
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "redirect:/login/welcome"; // 登入成功，重定向到 welcome 頁面
        } catch (Exception e) {
            return "redirect:/login/page?error=true"; // 登入失敗，重定向到 login 頁面並顯示錯誤
        }
    }


    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable int userId, @RequestBody User updatedUser) {
        try {
            User existingUser = userService.findUserById(userId);

            // Update user information except for the password
            existingUser.setName(updatedUser.getName());
            existingUser.setPhone(updatedUser.getPhone());
            // Do not update the password

            userService.saveUser(existingUser);

            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
    	userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
