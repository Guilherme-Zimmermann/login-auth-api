package com.example.loginauthapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.loginauthapi.dto.LoginRequestDto;
import com.example.loginauthapi.dto.NewPasswordDto;
import com.example.loginauthapi.dto.RegisterRequestDto;
import com.example.loginauthapi.dto.ResetPasswordDto;
import com.example.loginauthapi.dto.ResponseDto;
import com.example.loginauthapi.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/api/v1/auth")
public class AuthResource {
    
    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto data) {
        String token = authService.login(data.email(), data.password());
        return ResponseEntity.ok().body(new ResponseDto(token));    
    }
    
    @PostMapping("register")
    public ResponseEntity<ResponseDto> register(@RequestBody @Valid RegisterRequestDto data) {
        String token = authService.register(data.getName(), data.getEmail(), data.getPassword());
        return ResponseEntity.ok().body(new ResponseDto(token));
    }

    @PostMapping("reset-password")
    public ResponseEntity<ResponseDto> resetPassword(@RequestBody ResetPasswordDto data) {
        String token = authService.resetPassowrd(data.email());
        return ResponseEntity.ok().body(new ResponseDto(token));
    }

    @PostMapping("new-password")
    public ResponseEntity<ResponseDto> newPassword(@RequestBody NewPasswordDto data) {
        String token = authService.newPassword(data.token(), data.newPassword());
        return ResponseEntity.ok().body(new ResponseDto(token));
    }
}
