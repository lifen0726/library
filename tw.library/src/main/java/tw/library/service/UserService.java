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

	public User findByNameAndPsw(String name, String password) {
		return userRepository.findByNameAndPassword(name, password);
	}

	public List<User> findAllMembers() {
		return userRepository.findAll();
	}

	public User findByName(String name) {
		Optional<User> uResp = userRepository.findByName(name);
		if (uResp.isEmpty()) {
			throw new UsernameNotFoundException("Can't find member.");
		}
		return uResp.get();
	}

	public User saveMember(User member) {
		return userRepository.save(member);
	}

	public boolean existsByUsername(String username) {
		return userRepository.existsByName(username);
	}
	
	public boolean existsByPhone(String phone) {
		return userRepository.existsByPhone(phone);
	}

	public void deleteMember(int memberId) {

		userRepository.deleteById(memberId);
	}

	public User getMemberById(int memberId) {
		Optional<User> optionalMember = userRepository.findById(memberId);

		if (optionalMember.isPresent()) {
			return optionalMember.get();
		} else {
			throw new UserNotFoundException("Member not found with ID: " + memberId);
		}
	}

	

}
