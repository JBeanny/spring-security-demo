package com.example.spring_security_demo.services;

import com.example.spring_security_demo.dao.UserDao;
import com.example.spring_security_demo.dto.AuthenticationRequest;
import com.example.spring_security_demo.models.UserEntity;
import com.example.spring_security_demo.repository.UserRepository;
import com.example.spring_security_demo.utils.IdGenerator;
import com.example.spring_security_demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder, UserDao userDao,
            JwtUtils jwtUtils,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    public Map<String,Object> authenticate(AuthenticationRequest user) {
        Map<String,Object> response = new HashMap<>();

        try {
            // authenticate user credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail().toLowerCase(),user.getPassword())
            );

            // find user by email
            UserDetails userDetails = userDao.findUserByEmail(user.getEmail().toLowerCase());

            // if user credentials are correct and user found
            if(userDetails != null) {
                response.put("success",true);
                response.put("message","Login Successfully");
                response.put("token",jwtUtils.generateToken(userDetails));

                return response;
            }

        }catch(AuthenticationException e) {
            response.put("success",false);
            response.put("message","Failed to login");
            return response;
        }

        // if user credentials are incorrect or not found
        response.put("success",false);
        response.put("message","Failed to login");
        return response;
    }

    public Map<String,Object> register(UserEntity user) {
        Map<String,Object> response = new HashMap<>();

        try {
            if(user.getUsername().isEmpty() ||
                    user.getEmail().isEmpty() ||
                    user.getPassword().isEmpty()) {
                response.put("success",false);
                response.put("message","Invalid data");
            }

            // get user from db by email
            UserEntity userExists = userRepository.findUserByEmail(user.getEmail());

            if(userExists != null) {
                response.put("success",false);
                response.put("message","Email is already in used");
                return response;
            }

            // if success

            // encrypt password
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            // generate random Id
            user.setId(IdGenerator.generateId());
            // convert email to lowercase before storing into db
            user.setEmail(user.getEmail().toLowerCase());
            userRepository.save(user);
            response.put("success",true);
            response.put("message","Successfully Created");

            return response;

        } catch (Exception e) {
            response.put("success",false);
            response.put("message","Something went wrong");

            return response;
        }

    }
}
