package com.project.eventplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.eventplanner.payload.EventDTO;
import com.project.eventplanner.payload.EventResponse;
import com.project.eventplanner.service.EventService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/public/events")
    public ResponseEntity<EventResponse> allEvents(
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ) {
        return new ResponseEntity<>(this.eventService.getAllEvents(pageNumber, pageSize), HttpStatus.OK);
    }

    @PostMapping("/admin/events")
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody EventDTO eventDTO) {
        return new ResponseEntity<>(this.eventService.createEvent(eventDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/events/{eventId}")
    public ResponseEntity<EventDTO> deleteEvent(@PathVariable Long eventId) {
        return new ResponseEntity<>(this.eventService.deleteEvent(eventId), HttpStatus.OK);
    }

    @PutMapping("/admin/events/{eventId}")
    public ResponseEntity<EventDTO> updateEvent(@Valid @RequestBody EventDTO eventDTO, @PathVariable Long eventId) {
        return new ResponseEntity<>(this.eventService.updateEvent(eventDTO, eventId), HttpStatus.OK);
    }

}
