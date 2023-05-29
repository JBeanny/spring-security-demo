package com.example.spring_security_demo.repository;

import com.example.spring_security_demo.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {

    @Query("SELECT s FROM UserEntity s WHERE s.email = ?1")
//    Optional<User> findUserByEmail(String email);
    UserEntity findUserByEmail(String email);
}
