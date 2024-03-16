package tw.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.library.model.User; 

public interface UserRepository extends JpaRepository<User, Integer> { 
    public Optional<User> findByName(String username); 
	public Optional<User> findByPhone(String phone);
    public User findByNameAndPassword(String username, String password);
    Boolean existsByName(String username);
	public boolean existsByPhone(String phone);

}
