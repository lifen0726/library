package tw.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.library.exception.UserNotFoundException;
import tw.library.repository.UserRepository; 
import tw.library.model.User; 

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired 
    private UserRepository userRepository; 
    
    public User findByNameAndPsw(String username, String password) {
        return userRepository.findByUserNameAndPassword(username, password);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findByName(String username) {
        Optional<User> uResp = userRepository.findByUserName(username);
        if(uResp.isEmpty()) {
            throw new UsernameNotFoundException("Can't find user."); 
        }
        return uResp.get();
    }

    public User saveUser(User user) {
        return userRepository.save(user); 
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUserName(username); 
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId); 
    }

    public User getUserById(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId); 

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException("User not found with ID: " + userId); 
        }
    }
}
