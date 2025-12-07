package com.fooddelivery.user_service.controller;

import com.fooddelivery.user_service.dto.MenuDTO;
import com.fooddelivery.user_service.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<MenuDTO>> getMenuByRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(menuService.getMenuForRestaurant(restaurantId));
    }
}
