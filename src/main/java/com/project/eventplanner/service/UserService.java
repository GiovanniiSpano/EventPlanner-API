package com.project.eventplanner.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.eventplanner.dao.UserRepository;
import com.project.eventplanner.model.User;
import com.project.eventplanner.model.dto.UserDTO;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    final private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User saveUser(UserDTO userDTO) {
        Optional<User> userOptional = this.repo.findByUsername(userDTO.getUsername());

        if (userOptional.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome utente già in uso");

        User user = this.modelMapper.map(userDTO, User.class);

        user.setPassword(encoder.encode(user.getPassword()));
        return this.repo.save(user);
    }
    
}
