package com.eventbooking.event_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private String location;
    private int totalSeats;
    private int availableSeats;
}

