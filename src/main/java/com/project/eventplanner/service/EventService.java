package com.project.eventplanner.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.eventplanner.exception.APIException;
import com.project.eventplanner.exception.ResourceNotFoundException;
import com.project.eventplanner.model.Event;
import com.project.eventplanner.payload.EventDTO;
import com.project.eventplanner.payload.EventResponse;
import com.project.eventplanner.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    public EventResponse getAllEvents(int pageNumber, int pageSize) {

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize);

        Page<Event> eventPage = this.repo.findAll(pageDetails);

        List<Event> events = eventPage.getContent();

        if (events.isEmpty()) {
            throw new APIException("Nessun evento creato finora");
        }

        List<EventDTO> eventDTOs = events.stream()
                .map(event -> this.modelMapper.map(event, EventDTO.class))
                .toList();

        EventResponse eventResponse = new EventResponse();

        eventResponse.setContent(eventDTOs);
        eventResponse.setPageNumber(eventPage.getNumber());
        eventResponse.setPageSize(eventPage.getSize());
        eventResponse.setTotalElements(eventPage.getTotalElements());
        eventResponse.setTotalPages(eventPage.getTotalPages());
        eventResponse.setLastPage(eventPage.isLast());

        return eventResponse;

    }

    public EventDTO createEvent(EventDTO eventDTO) {

        Event event = this.modelMapper.map(eventDTO, Event.class);

        return this.modelMapper.map(this.repo.save(event), EventDTO.class);

    }

    public EventDTO deleteEvent(Long eventId) {

        Event event = this.repo.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("'Event'", "eventId", eventId));

        EventDTO deletedEventDTO = this.modelMapper.map(event, EventDTO.class);

        this.repo.delete(event);

        return deletedEventDTO;

    }

    public EventDTO updateEvent(EventDTO eventDTO, Long eventId) {

        Event event = this.repo.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("'Event'", "eventId", eventId));

        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());

        EventDTO updatedEventDTO = this.modelMapper.map(this.repo.save(event), EventDTO.class);

        return updatedEventDTO;

    }

}
