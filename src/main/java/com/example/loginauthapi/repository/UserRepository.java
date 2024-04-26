package com.example.loginauthapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loginauthapi.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    
}
