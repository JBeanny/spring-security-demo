package com.example.spring_security_demo.dao;

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import com.example.spring_security_demo.services.CustomUserDetailService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDao {

    private final CustomUserDetailService customUserDetailService;

    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
            new User(
                    "jbeanny@gmail.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
            ),
            new User(
                    "bean@gmail.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
            )
    );

    public UserDao(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }


    public UserDetails findUserByEmail(String email) {
        return customUserDetailService.loadUserByUsername(email);
    }

}
