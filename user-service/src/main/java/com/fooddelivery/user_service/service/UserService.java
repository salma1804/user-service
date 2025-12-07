package com.fooddelivery.user_service.service;

import com.fooddelivery.user_service.dto.UserDTO;
import com.fooddelivery.user_service.model.User;

public interface UserService {
    UserDTO registerUser(User user);
    UserDTO authenticateUser(String email, String password);
    UserDTO getUserByEmail(String email);
    UserDTO updateUser(Long id, User user);
    User getUserById(Long id); // For internal use (e.g., when adding rating)
}
