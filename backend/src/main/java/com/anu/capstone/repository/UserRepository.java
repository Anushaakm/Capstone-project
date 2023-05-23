package com.anu.capstone.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anu.capstone.domain.User;


public interface UserRepository extends JpaRepository<User, Long>{
    
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
    List<User> findAllByEmail(String email);
    boolean existsByEmail(String email);

}
