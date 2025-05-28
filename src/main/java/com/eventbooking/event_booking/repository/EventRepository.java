package com.eventbooking.event_booking.repository;

import com.eventbooking.event_booking.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    // Additional query methods can be defined here
}