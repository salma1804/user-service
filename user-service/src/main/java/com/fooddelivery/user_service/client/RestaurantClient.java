package com.fooddelivery.user_service.client;

import com.fooddelivery.user_service.dto.MenuDTO;
import com.fooddelivery.user_service.dto.RestaurantDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "restaurant-service") // service name in Eureka or application.yml
public interface RestaurantClient {

    @GetMapping("/restaurants")
    List<RestaurantDTO> getAllRestaurants();

    @GetMapping("/restaurants/{id}")
    RestaurantDTO getRestaurantById(@PathVariable("id") Long id);

    @GetMapping("/menu-items/restaurant/{restaurantId}")
    List<MenuDTO> getMenuByRestaurant(@PathVariable("restaurantId") Long restaurantId);
}

