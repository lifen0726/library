package tw.library.service;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import tw.library.model.User; 


public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
    	System.out.println(phone);
    	User user = userService.findByPhone(phone);
    	System.out.println(user);
        if (user == null) {
        	throw new UsernameNotFoundException("找不到用户: " + phone);
        }
        userService.updateUserLastLoginTime(phone);

        List<GrantedAuthority> authorities = getAuthorities();

        
        return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(), authorities);
    }

    private List<GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}


