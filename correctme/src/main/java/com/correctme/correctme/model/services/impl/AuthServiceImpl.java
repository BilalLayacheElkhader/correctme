package com.correctme.correctme.model.services.impl;

import com.correctme.correctme.model.dao.AuthResponse;
import com.correctme.correctme.model.dao.request.AuthenticationRequest;
import com.correctme.correctme.model.dao.response.RegisterRequest;
import com.correctme.correctme.model.domain.Role;
import com.correctme.correctme.model.domain.User;
import com.correctme.correctme.model.repository.UserRepository;
import com.correctme.correctme.model.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            request.setName("Anonymous");
        }
        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new RuntimeException("EMAIL CANNOT BY EMPTY");
        }
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new RuntimeException("PASSWORD CANNOT BY EMPTY");
        }

        if (!"Anonymous".equals(request.getName())) {
            userRepository.findByName(request.getName())
                    .ifPresent(pl -> {
                        throw new RuntimeException("Name " + pl.getName() + " not available.");
                    });
        }
        userRepository.findPlayerByEmail(request.getEmail())
                .ifPresent(pl -> {
                    throw new RuntimeException("Email not avaible.");
                });
        var player = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(player);
        var jwtToken = jwtService.generateToken(player);
        return AuthResponse.builder()
                .token(jwtToken).build();
    }

    @Override
    public AuthResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var player = userRepository.findPlayerByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(player);
        return AuthResponse.builder().token(jwtToken).build();
    }
}
