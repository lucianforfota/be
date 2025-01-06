package com.onlineshop.be.controllers;

import com.onlineshop.be.dtos.AuthRequestDTO;

import com.onlineshop.be.entities.User;
import com.onlineshop.be.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(userService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(userService.register(request));
    }
}