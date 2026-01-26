package com.fooddelivery.user_service.controller;

import com.fooddelivery.user_service.dto.ErrorResponse;
import com.fooddelivery.user_service.dto.LoginRequest;
import com.fooddelivery.user_service.dto.LoginResponse;
import com.fooddelivery.user_service.model.User;
import com.fooddelivery.user_service.repository.UserRepository;
import com.fooddelivery.user_service.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        // Use loginRequest.username as email
        Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Invalid credentials"));
        }

        User user = optionalUser.get();

        // Check password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Invalid credentials"));
        }

        // Create JWT
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());

        String token = tokenProvider.generateToken(user.getUsername(), claims);

        // Return token and username (here we use email as username)
        return ResponseEntity.ok(new LoginResponse(token, user.getUsername()));
    }
}
