package tw.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.library.exception.UserNotFoundException;
import tw.library.model.User;
import tw.library.repository.UserRepository;

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

	public User findByNameAndPsw(String name, String password) {
		return userRepository.findByNameAndPassword(name, password);
	}

	public User findByName(String name) {
		Optional<User> uResp = userRepository.findByName(name);
		if (uResp.isEmpty()) {
			throw new UsernameNotFoundException("Can't find member.");
		}
		return uResp.get();
	}

	public boolean existsByUsername(String username) {
		return userRepository.existsByName(username);
	}
	
	public boolean existsByPhone(String phone) {
		return userRepository.existsByPhone(phone);
	}

	public User getMemberById(int memberId) {
		Optional<User> optionalMember = userRepository.findById(memberId);

		if (optionalMember.isPresent()) {
			return optionalMember.get();
		} else {
			throw new UserNotFoundException("Member not found with ID: " + memberId);
		}
	}

	public User findByPhone(String phone) {
		Optional<User> uResp = userRepository.findByPhone(phone);
		System.out.println("uResp:"+uResp);
		if (uResp.isEmpty()) {
			throw new UserNotFoundException("Can't find member.");
		}
		return uResp.get();
	}




	

}
