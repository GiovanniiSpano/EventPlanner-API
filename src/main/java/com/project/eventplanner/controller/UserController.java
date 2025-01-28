package com.project.eventplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.eventplanner.model.User;
import com.project.eventplanner.service.JwtService;
import com.project.eventplanner.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return this.service.saveUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return this.jwtService.generateToken(user.getUsername());
        } 

        return "Login Failed";
    }

}
