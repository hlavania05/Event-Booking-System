package com.eventbooking.event_booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.eventbooking.event_booking.dto.ApiResponse;
import com.eventbooking.event_booking.dto.EventDto;
import com.eventbooking.event_booking.model.Event;
import com.eventbooking.event_booking.service.EventService;

import jakarta.validation.Valid;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@Validated
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> createEvent(@Valid @RequestBody EventDto dto) {
        try {
            Event event = new Event(dto.getTitle(), dto.getDescription(), dto.getDateTime(),
                    dto.getLocation(), dto.getTotalSeats(), dto.getAvailableSeats());
            eventService.createEvent(event);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(true, "Event created successfully!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Failed to create event: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEvents() {
        try {
            List<Event> events = eventService.getAllEvents();
            return ResponseEntity.ok(events);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Something went wrong."));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id) {
        try {
            Event event = eventService.getEventById(id);
            return ResponseEntity.ok(event);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> updateEvent(@PathVariable Long id, @Valid @RequestBody EventDto dto) {
        try {
            Event event = new Event(
                    dto.getTitle(),
                    dto.getDescription(),
                    dto.getDateTime(),
                    dto.getLocation(),
                    dto.getTotalSeats(),
                    dto.getAvailableSeats());

            eventService.updateEvent(id, event);

            return ResponseEntity.ok(new ApiResponse(true, "Event updated successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Failed to update event"));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteEvent(@PathVariable Long id) {
        try {
            eventService.deleteEvent(id);
            return ResponseEntity.ok(new ApiResponse(true, "Event deleted successfully."));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Failed to delete event."));
        }
    }
}
