package com.fooddelivery.user_service.service;

import com.fooddelivery.user_service.dto.UserDTO;
import com.fooddelivery.user_service.exception.UserNotFoundException;
import com.fooddelivery.user_service.model.User;
import com.fooddelivery.user_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder

    @Override
    public UserDTO registerUser(User user) {
        // Hash password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        return new UserDTO(savedUser.getEmail(), savedUser.getFullName(), savedUser.getRole(),savedUser.getUsername(),user.getId());
    }


    @Override
    public UserDTO getUserById(Long id) {
        User user= userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDTO(user.getEmail(), user.getFullName(), user.getRole(), user.getUsername(), user.getId());

    }



    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return new UserDTO(user.getEmail(), user.getFullName(), user.getRole(), user.getUsername(), user.getId());
    }

    @Override
    public UserDTO updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setFullName(updatedUser.getFullName());

        // Only update password if provided
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        userRepository.save(user);

        return new UserDTO(user.getEmail(), user.getFullName(), user.getRole(), user.getUsername(),user.getId());
    }


    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
