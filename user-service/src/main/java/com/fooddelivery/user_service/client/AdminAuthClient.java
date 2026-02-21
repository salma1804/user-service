package com.fooddelivery.user_service.client;

import com.fooddelivery.user_service.dto.LoginResponse;
import com.fooddelivery.user_service.dto.RatingDTO;
import com.fooddelivery.user_service.dto.UserDTO;
import com.fooddelivery.user_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "admin-service",
        url = "http://localhost:8081",
        configuration = com.fooddelivery.user_service.config.FeignClientConfig.class
)
public interface AdminAuthClient {

    // =====================
    // AUTH (PUBLIC)
    // =====================

    @PostMapping("/api/admin/auth/login")
    LoginResponse login(@RequestBody LoginRequest request);

    // =====================
    // PROTECTED ENDPOINTS
    // =====================

    @DeleteMapping("/api/admin/users/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable("id") Long id);

    @GetMapping("/api/admin/users/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id);

    @GetMapping("/api/admin/users/{userId}/ratings")
    ResponseEntity<List<RatingDTO>> getRatingsByUser(@PathVariable("userId") Long userId);

    @GetMapping("/api/admin/users/email/{email}")
    ResponseEntity<UserDTO> getUserByEmail(@PathVariable("email") String email);

    @PostMapping("/api/admin/users/register")
    ResponseEntity<UserDTO> registerUser(@RequestBody User user);

    // =====================
    // LOGIN REQUEST DTO
    // =====================

    class LoginRequest {
        private String email;
        private String password;

        public LoginRequest() {}

        public LoginRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}