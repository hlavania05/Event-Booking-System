package com.eventbooking.event_booking.controller;

import com.eventbooking.event_booking.dto.BookingDTO;
import com.eventbooking.event_booking.model.Booking;
import com.eventbooking.event_booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<String> bookEvent(@RequestParam Long userId, @RequestParam Long eventId) {
        String response = bookingService.bookEvent(userId, eventId);
        if (response.equals("Booking successful")) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDTO>> getUserBookings(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getUserBookings(userId));
    }

    @DeleteMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId, @RequestParam Long userId) {
        String response = bookingService.cancelBooking(bookingId, userId);
        if (response.equals("Booking cancelled successfully")) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }
}
