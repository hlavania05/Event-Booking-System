package com.eventbooking.event_booking.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.eventbooking.event_booking.dto.AuthResponseDto;
import com.eventbooking.event_booking.model.Role;
import com.eventbooking.event_booking.model.User;
import com.eventbooking.event_booking.repository.RoleRepository;
import com.eventbooking.event_booking.repository.UserRepository;
import com.eventbooking.event_booking.security.JwtTokenProvider;


import java.util.Collections;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    public AuthResponseDto registerUser(String name, String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email is already in use!");
        }
        
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("User Role not set."));

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(userRole);

        userRepository.save(user);

        String mytoken = authenticateUser(email, password);

        return new AuthResponseDto("User Registered Successfully", mytoken);
    }

    public String authenticateUser(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        return tokenProvider.generateToken(authentication);
    }
}

