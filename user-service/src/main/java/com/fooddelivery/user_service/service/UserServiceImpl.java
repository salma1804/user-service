package com.fooddelivery.user_service.service;

import com.fooddelivery.user_service.dto.UserDTO;
import com.fooddelivery.user_service.exception.UserNotFoundException;
import com.fooddelivery.user_service.model.User;
import com.fooddelivery.user_service.repository.UserRepository;
import com.fooddelivery.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO registerUser(User user) {
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getEmail(), savedUser.getFullName(), savedUser.getRole());
    }
    @Override
    public Optional<User> authenticateUser(String email, String password) {
        if (email == null || password == null) {
            return Optional.empty();
        }
        String trimmedEmail = email.trim();
        String trimmedPassword = password.trim();

        return userRepository.findByEmail(trimmedEmail)
                .filter(user -> trimmedPassword.equals(user.getPassword()));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return new UserDTO(user.getEmail(), user.getFullName(), user.getRole());
    }

    @Override
    public UserDTO updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setFullName(updatedUser.getFullName());
        user.setPassword(updatedUser.getPassword()); // consider encoding
        userRepository.save(user);
        return new UserDTO(user.getEmail(), user.getFullName(), user.getRole());
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
