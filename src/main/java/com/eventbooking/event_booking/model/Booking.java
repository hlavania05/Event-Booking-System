package com.eventbooking.event_booking.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Booking karta user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Event jiske liye booking ki
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    // Booking date/time (optional)
    private LocalDateTime bookingTime;

    public Booking(User user, Event event, LocalDateTime bookingTime) {
    this.user = user;
    this.event = event;
    this.bookingTime = bookingTime;
}

}
