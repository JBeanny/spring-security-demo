package com.example.spring_security_demo.controllers;

import com.example.spring_security_demo.models.UserEntity;
import com.example.spring_security_demo.repository.UserRepository;
import com.example.spring_security_demo.dao.UserDao;
import com.example.spring_security_demo.dto.AuthenticationRequest;
import com.example.spring_security_demo.services.UserService;
import com.example.spring_security_demo.utils.CustomResponse;
import com.example.spring_security_demo.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private UserDao userDao;
    private final JwtUtils jwtUtils;

    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils,UserDao userDao,UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userDao = userDao;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest request) {
        Map<String,Object> response = userService.authenticate(request);

        return (Boolean) response.get("success") ?
                ResponseEntity.status(200).body(response) :
                ResponseEntity.status(400).body(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody UserEntity request) {

        Map<String,Object> response = userService.register(request);

        return (Boolean) response.get("success") ?
                ResponseEntity.status(201).body(response) :
                ResponseEntity.status(400).body(response);
    }
}
