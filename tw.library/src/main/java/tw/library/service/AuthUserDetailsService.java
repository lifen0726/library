package tw.library.service;

import tw.library.model.User;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // ...

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = userService.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("找不到用戶");
        }

        // 確保密碼被正確編碼
        List<GrantedAuthority> authorities = getAuthorities();

        // 返回已編碼密碼和權限的 UserDetails
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
    }
    
    private List<GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // ...
}
