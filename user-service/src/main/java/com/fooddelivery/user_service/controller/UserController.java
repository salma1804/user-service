package com.fooddelivery.user_service.controller;

import com.fooddelivery.user_service.dto.RatingDTO;
import com.fooddelivery.user_service.dto.UserDTO;
import com.fooddelivery.user_service.model.Rating;
import com.fooddelivery.user_service.model.User;
import com.fooddelivery.user_service.service.RatingService;
import com.fooddelivery.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    private final RatingService ratingService;

    // User registration
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody User user) {
        UserDTO registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (userOptional.isPresent()) {
            String token = jwtUtil.generateToken(userOptional.get().getUsername());
            return ResponseEntity.ok().body("Bearer " + token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }


    // Get user by email
    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        UserDTO user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    // Update user profile
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody User user) {
        UserDTO updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }
    // Add a rating (restaurant or delivery)
    @PostMapping("/ratings")
    public ResponseEntity<RatingDTO> addRating(@RequestBody RatingDTO dto) {
        Rating saved = ratingService.addRatingFromDTO(dto);
        return ResponseEntity.ok(ratingService.toDTO(saved));
    }

    // Get ratings by user
    @GetMapping("/{userId}/ratings")
    public ResponseEntity<List<RatingDTO>> getRatingsByUser(@PathVariable Long userId) {
        List<RatingDTO> ratings = ratingService.getRatingsByUser(userId);
        return ResponseEntity.ok(ratings);
    }
}
