package tw.library.service;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tw.library.model.User; // 確保導入了正確的 User 類


public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	System.out.println("username:"+username);
    	User user = userService.findByName(username);
        System.out.println("User:"+user);
        if (user == null) {
        	throw new UsernameNotFoundException("找不到用户: " + username);
        }

        List<GrantedAuthority> authorities = getAuthorities();

        
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
    }

    private List<GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}


