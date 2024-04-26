package com.example.loginauthapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.loginauthapi.entities.User;
import com.example.loginauthapi.service.UserService;

@Controller
@RequestMapping("/auth/v1/api")
public class UserResource {
    
    @Autowired
    private UserService service;

    public ResponseEntity<List<User>> findAll() {
        List<User> users = service.findAll();
        return ResponseEntity.ok().body(users);
    }
}
