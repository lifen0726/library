package tw.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.library.exception.UserNotFoundException;
import tw.library.model.User;
import tw.library.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
    public User findUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    
    public User saveUser(User user) {
    	return userRepository.save(user);
    }

    
    public void updateUser(User user) {
        userRepository.save(user);
    }

    
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

	public boolean existsByUsername(String username) {
		return userRepository.existsByName(username);
	}
	
	public boolean existsByPhone(String phone) {
		return userRepository.existsByPhone(phone);
	}

	public User findByPhone(String phone) {
		Optional<User> uResp = userRepository.findByPhone(phone);
		System.out.println("uResp:"+uResp);
		if (uResp.isEmpty()) {
			throw new UserNotFoundException("Can't find member.");
		}
		return uResp.get();
	}
	public void updateUserLastLoginTime(String phone) {
        User user = findByPhone(phone);
        System.out.println("user登入"+user);
        user.setLastLoginTime(LocalDateTime.now());   
        userRepository.save(user);
    }
}
