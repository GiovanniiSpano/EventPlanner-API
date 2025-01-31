package com.project.eventplanner.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.eventplanner.exception.APIException;
import com.project.eventplanner.model.User;
import com.project.eventplanner.payload.UserDTO;
import com.project.eventplanner.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    final private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User saveUser(UserDTO userDTO) {
        Optional<User> userOptional = this.repo.findByUsername(userDTO.getUsername());

        if (userOptional.isPresent()) throw new APIException("Nome utente già in uso");

        User user = this.modelMapper.map(userDTO, User.class);

        user.setPassword(encoder.encode(user.getPassword()));
        return this.repo.save(user);
    }
    
}
