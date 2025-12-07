package com.fooddelivery.user_service.service;

import com.fooddelivery.user_service.client.RestaurantClient;
import com.fooddelivery.user_service.dto.RestaurantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRestaurantService {

    private final RestaurantClient restaurantClient;

    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantClient.getAllRestaurants();
    }

    public RestaurantDTO getRestaurantById(Long id) {
        return restaurantClient.getRestaurantById(id);
    }
}

