package com.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.Users; 

public interface UserRepository extends JpaRepository<Users, Integer> { 
    public Optional<Users> findByUserName(String username); 
    public Users findByUserNameAndPassword(String username, String password);
    Boolean existsByUserName(String username);
}

