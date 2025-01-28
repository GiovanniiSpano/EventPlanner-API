package com.project.eventplanner.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.eventplanner.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByUsername(String username);
}
