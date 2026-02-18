package com.fooddelivery.user_service.controller;

import com.fooddelivery.user_service.dto.RatingDTO;
import com.fooddelivery.user_service.dto.UserDTO;
import com.fooddelivery.user_service.model.Rating;
import com.fooddelivery.user_service.model.User;
import com.fooddelivery.user_service.service.RatingService;
import com.fooddelivery.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final RatingService ratingService;

    // User registration
//    @PostMapping("/register")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<UserDTO> registerUser(@RequestBody User user) {
//        UserDTO registeredUser = userService.registerUser(user);
//        return ResponseEntity.ok(registeredUser);
//    }

    // Get user by email
//    @GetMapping("/{email}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
//        UserDTO user = userService.getUserByEmail(email);
//        return ResponseEntity.ok(user);
//    }

    // Update user profile
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")

    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody User user) {
        UserDTO updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    // Add a rating (restaurant or delivery)
    @PostMapping("/ratings")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<RatingDTO> addRating(@RequestBody RatingDTO dto) {
        Rating saved = ratingService.addRatingFromDTO(dto);
        return ResponseEntity.ok(ratingService.toDTO(saved));
    }

//    // Get ratings by user
//    @GetMapping("/{userId}/ratings")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<List<RatingDTO>> getRatingsByUser(@PathVariable Long userId) {
//        List<RatingDTO> ratings = ratingService.getRatingsByUser(userId);
//        return ResponseEntity.ok(ratings);
//    }
    // Delete user by ID
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
//        boolean deleted = userService.deleteUser(id);
//        if (deleted) {
//            return ResponseEntity.ok().body("User deleted successfully");
//        } else {
//            return ResponseEntity.badRequest().body("User not found or could not be deleted");
//        }
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
//        UserDTO user = userService.getUserById(id);
//        return ResponseEntity.ok(user);
//    }


}
