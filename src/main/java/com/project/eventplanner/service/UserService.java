package com.project.eventplanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.eventplanner.dao.UserRepository;
import com.project.eventplanner.model.User;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;
    final private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return this.repo.save(user);
    }
    
}
