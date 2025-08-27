package org.codigo.booking.user.service;

import org.codigo.booking.user.dto.LoginRequest;
import org.codigo.booking.user.model.User;
import org.codigo.booking.user.model.UserRequest;
import org.codigo.booking.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(UserRequest input) {
        input.setPassword(passwordEncoder.encode(input.getPassword()));
        var user = userRepository.save(
                        User.builder()
                                .password(input.getPassword())
                                .fullName(input.getFullName())
                                .email(input.getEmail())
                                .country(input.getCountry())
                                .build()
        );
        user.setPassword(null);
        return user;
    }

    public User authenticate(LoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
