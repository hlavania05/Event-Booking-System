package com.eventbooking.event_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventbooking.event_booking.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}