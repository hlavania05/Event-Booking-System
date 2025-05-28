package com.eventbooking.event_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eventbooking.event_booking.model.Role;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
