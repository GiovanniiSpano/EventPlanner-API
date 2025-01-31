package com.project.eventplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.eventplanner.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    
}
