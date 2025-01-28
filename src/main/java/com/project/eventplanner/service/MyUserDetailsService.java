package com.project.eventplanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.eventplanner.dao.UserRepository;
import com.project.eventplanner.model.User;
import com.project.eventplanner.model.UserPrincipal;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.repo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Utente Non Trovato"));

        return new UserPrincipal(user);
    }
    
}
