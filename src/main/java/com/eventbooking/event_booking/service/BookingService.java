package com.eventbooking.event_booking.service;

import com.eventbooking.event_booking.dto.BookingDTO;
import com.eventbooking.event_booking.model.*;
import com.eventbooking.event_booking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public String bookEvent(Long userId, Long eventId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));

        // Check available seats
        if (event.getAvailableSeats() <= 0) {
            return "No seats available for this event";
        }

        // Check if user already booked this event
        if (bookingRepository.existsByUserAndEvent(user, event)) {
            return "User already booked this event";
        }

        // Reduce available seats
        event.setAvailableSeats(event.getAvailableSeats() - 1);
        eventRepository.save(event);

        // Create booking
        Booking booking = new Booking(user, event, LocalDateTime.now());
        bookingRepository.save(booking);

        return "Booking successful";
    }

    public List<BookingDTO> getUserBookings(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);

        return bookings.stream().map(b -> new BookingDTO(
                b.getId(),
                b.getEvent().getTitle(),
                b.getUser().getName(),
                b.getBookingTime())).collect(Collectors.toList());
    }

    @Transactional
    public String cancelBooking(Long bookingId, Long userId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Verify booking belongs to user
        if (!booking.getUser().getId().equals(userId)) {
            return "Unauthorized cancellation attempt";
        }

        Event event = booking.getEvent();

        // Increase available seats
        event.setAvailableSeats(event.getAvailableSeats() + 1);
        eventRepository.save(event);

        bookingRepository.delete(booking);
        return "Booking cancelled successfully";
    }
}
