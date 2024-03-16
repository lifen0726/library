package tw.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.library.model.User; 

public interface UserRepository extends JpaRepository<User, Integer> { 
    public Optional<User> findByUserName(String username); 
    public User findByUserNameAndPassword(String username, String password);
    Boolean existsByUserName(String username);
}

