package com.project.eventplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.eventplanner.model.User;
import com.project.eventplanner.payload.UserDTO;
import com.project.eventplanner.service.JwtService;
import com.project.eventplanner.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserDTO user) {

        return new ResponseEntity<>(this.service.saveUser(user), HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody User user) {

        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(this.jwtService.generateToken(user.getUsername()), HttpStatus.OK);
        } 

        return new ResponseEntity<>("Username o password errate", HttpStatus.FORBIDDEN);
    }

}
