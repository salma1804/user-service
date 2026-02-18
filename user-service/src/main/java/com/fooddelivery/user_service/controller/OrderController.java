package com.fooddelivery.user_service.controller;

import com.fooddelivery.user_service.dto.CartItemDTO;
import com.fooddelivery.user_service.dto.OrderRequestDTO;
import com.fooddelivery.user_service.dto.OrderResponseDTO;
import com.fooddelivery.user_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.ok(orderService.placeOrder(orderRequestDTO));
    }
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<OrderResponseDTO>> getUserOrderHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getUserOrderHistory(userId));
    }
    @PostMapping("/cart/add")
    public ResponseEntity<CartItemDTO> addToCart(@RequestBody CartItemDTO cartItem) {
        // Pass both cartItem and userId from DTO to the service (or Feign client)
        CartItemDTO addedItem = orderService.addToCart( cartItem.getUserId(),cartItem);
        return ResponseEntity.ok(addedItem);
    }


}
