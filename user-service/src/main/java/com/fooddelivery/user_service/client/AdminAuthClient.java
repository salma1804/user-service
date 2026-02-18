package com.fooddelivery.user_service.client;

import com.fooddelivery.user_service.dto.RatingDTO;
import com.fooddelivery.user_service.dto.UserDTO;
import com.fooddelivery.user_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "admin-service", url = "http://localhost:8081")
public interface AdminAuthClient {

    @PostMapping("/api/admin/auth/login")
    LoginResponse login(@RequestBody LoginRequest loginRequest);

    class LoginRequest {
        private String email;
        private String password;

        // constructors, getters, setters
    }

    class LoginResponse {
        private String token;
        private String email;
        // getters, setters
    }

    @DeleteMapping("/api/admin/users/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable("id") Long id);

    @GetMapping("/api/admin/users/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id);

    @GetMapping("/api/admin/users/{userId}/ratings")
    ResponseEntity<List<RatingDTO>> getRatingsByUser(@PathVariable("userId") Long userId);

    @GetMapping("/api/admin/users/email/{email}")
    ResponseEntity<UserDTO> getUserByEmail(@PathVariable("email") String email);

    @PostMapping("/api/admin/users/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody User user);
}

