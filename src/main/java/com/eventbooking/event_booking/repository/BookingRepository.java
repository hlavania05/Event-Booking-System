package com.eventbooking.event_booking.repository;

import com.eventbooking.event_booking.model.Booking;
import com.eventbooking.event_booking.model.Event;
import com.eventbooking.event_booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    boolean existsByUserAndEvent(User user, Event event);

}

