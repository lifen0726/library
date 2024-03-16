package tw.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.library.exception.UserNotFoundException;
import tw.library.model.User;
import tw.library.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

	@Autowired 
    private MemberRepository memberRepository;
	
	public User findByNameAndPsw(String name, String password) {
		return memberRepository.findByNameAndPassword(name, password);
	}

    public List<User> findAllMembers() {
        return memberRepository.findAll();
    }

    public User findByName(String name) {
        Optional<User> uResp = memberRepository.findByName(name);
        if(uResp.isEmpty()) {
        	throw new UsernameNotFoundException("Can't find member.");
        }
        return uResp.get();
    }

    public User saveMember(User member) {
        return memberRepository.save(member);
    }

        public boolean existsByUsername(String username) {
            return memberRepository.existsByName(username);
        }


    public void deleteMember(int memberId) {
    	
        memberRepository.deleteById(memberId);
    }

    public User getMemberById(int memberId) {
        Optional<User> optionalMember = memberRepository.findById(memberId);

        if (optionalMember.isPresent()) {
            return optionalMember.get();
        } else {
            throw new UserNotFoundException("Member not found with ID: " + memberId);
        }
    }

}

