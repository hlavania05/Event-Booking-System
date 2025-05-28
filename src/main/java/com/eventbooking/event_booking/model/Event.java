package com.eventbooking.event_booking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @Future(message = "Event date and time must be in the future")
    @NotNull(message = "Date and time is required")
    private LocalDateTime dateTime;

    @NotBlank(message = "Location is mandatory")
    @Size(max = 200, message = "Location must be less than 200 characters")
    private String location;

    @Min(value = 1, message = "Total seats must be at least 1")
    private int totalSeats;

    @Min(value = 0, message = "Available seats cannot be negative")
    private int availableSeats;

    public Event(String title, String description, LocalDateTime dateTime, String location, int totalSeats, int availableSeats) {
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.location = location;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }
}
