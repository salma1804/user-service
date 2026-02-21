package com.fooddelivery.user_service.controller;

import com.fooddelivery.user_service.client.AdminAuthClient;
import com.fooddelivery.user_service.dto.LoginResponse;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AdminAuthClient adminAuthClient;

    public AuthController(AdminAuthClient adminAuthClient) {
        this.adminAuthClient = adminAuthClient;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminAuthClient.LoginRequest loginRequest) {
        try {
            LoginResponse response = adminAuthClient.login(loginRequest);
            return ResponseEntity.ok(response);
        } catch (FeignException e) {
            return ResponseEntity.status(e.status()).body(e.getMessage());
        }
    }
}
//@Autowired
//private JwtTokenProvider tokenProvider;
//
//@Autowired
//private UserRepository userRepository;
//
//@Autowired
//private PasswordEncoder passwordEncoder;
//
//@PostMapping("/login")
//public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//
//    // Use loginRequest.username as email
//    Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());
//    if (optionalUser.isEmpty()) {
//        return ResponseEntity.badRequest()
//                .body(new ErrorResponse("Invalid credentials"));
//    }
//
//    User user = optionalUser.get();
//
//    // Check password
//    if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
//        return ResponseEntity.badRequest()
//                .body(new ErrorResponse("Invalid credentials"));
//    }
//
//    // Create JWT
//    Map<String, Object> claims = new HashMap<>();
//    claims.put("role", user.getRole());
//
//    String token = tokenProvider.generateToken(user.getUsername(), claims);
//
//    // Return token and username (here we use email as username)
//    return ResponseEntity.ok(new LoginResponse(token, user.getUsername()));
//}
