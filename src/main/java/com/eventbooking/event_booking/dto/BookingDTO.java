package com.eventbooking.event_booking.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long bookingId;
    private String eventTitle;
    private String userName;
    private LocalDateTime bookingTime;
}
