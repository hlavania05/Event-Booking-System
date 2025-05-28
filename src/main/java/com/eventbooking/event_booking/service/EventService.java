package com.eventbooking.event_booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventbooking.event_booking.dto.EventDto;
import com.eventbooking.event_booking.model.Event;
import com.eventbooking.event_booking.repository.EventRepository;


import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event) {
        try {
            return eventRepository.save(event);
        } catch (Exception e) {
            throw new RuntimeException("Error saving event: " + e.getMessage());
        }
    }

    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        if (events.isEmpty()) {
            throw new RuntimeException("No events found.");
        }
        return events;
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }

    public void updateEvent(Long id, Event eventDetails) {
        Event event = getEventById(id);
        event.setTitle(eventDetails.getTitle());
        event.setDescription(eventDetails.getDescription());
        event.setDateTime(eventDetails.getDateTime());
        event.setLocation(eventDetails.getLocation());
        event.setTotalSeats(eventDetails.getTotalSeats());
        event.setAvailableSeats(eventDetails.getAvailableSeats());
        eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        Event event = getEventById(id);
        eventRepository.delete(event);
    }
}
