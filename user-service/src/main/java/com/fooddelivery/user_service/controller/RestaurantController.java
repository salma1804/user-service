package com.fooddelivery.user_service.controller;

import com.fooddelivery.user_service.dto.RestaurantDTO;
import com.fooddelivery.user_service.service.UserRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final UserRestaurantService userRestaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        List<RestaurantDTO> restaurants = userRestaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable Long id) {
        RestaurantDTO restaurant = userRestaurantService.getRestaurantById(id);
        return ResponseEntity.ok(restaurant);
    }
}

