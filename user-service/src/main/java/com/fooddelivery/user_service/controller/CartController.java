package com.fooddelivery.user_service.controller;

import com.fooddelivery.user_service.dto.CartItemDTO;
import com.fooddelivery.user_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartItemDTO> addToCart(@RequestBody CartItemDTO cartItem) {
        return ResponseEntity.ok(cartService.addToCart(cartItem));
    }
}
