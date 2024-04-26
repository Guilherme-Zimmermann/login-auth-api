package com.example.loginauthapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.loginauthapi.entities.User;
import com.example.loginauthapi.infra.security.TokenService;
import com.example.loginauthapi.repository.UserRepository;
import com.example.loginauthapi.service.exceptions.ResourceNotFoundException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException());
        if (passwordEncoder.matches(password, user.getPassword())) {
            String token = tokenService.generateToken(user);
            return token;
        }
        throw new RuntimeException();
    }

    public String register(String name, String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        
        if(user.isEmpty()) {
            User newUser = new User();
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setPassword(passwordEncoder.encode(password));
            userRepository.save(newUser);
            
            String token = tokenService.generateToken(newUser);
            return token;
        }
        throw new RuntimeException();
    }

    public String resetPassowrd(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException());
        String token = tokenService.generateToken(user);
        return token;
    }

    public String newPassword(String token, String newPassword) {
        String email = tokenService.validateToken(token);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException());

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        
        String newToken = tokenService.generateToken(user);
        return newToken;
    }
}
