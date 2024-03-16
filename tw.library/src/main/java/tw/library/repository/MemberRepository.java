package tw.library.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.library.model.User;

public interface MemberRepository extends JpaRepository<User, Integer> {
	public Optional<User> findByName(String name);
	public User findByNameAndPassword(String name, String password);
	Boolean existsByName(String username);
}
