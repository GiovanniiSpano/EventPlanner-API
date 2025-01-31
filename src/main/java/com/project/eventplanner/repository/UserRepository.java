package com.project.eventplanner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.eventplanner.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);
}
