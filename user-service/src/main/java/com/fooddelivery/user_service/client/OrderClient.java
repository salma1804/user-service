package com.fooddelivery.user_service.client;

import com.fooddelivery.user_service.dto.CartItemDTO;
import com.fooddelivery.user_service.dto.OrderRequestDTO;
import com.fooddelivery.user_service.dto.OrderResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrderClient {

    @PostMapping("/api/cart/add")
    CartItemDTO addToCart(@RequestBody CartItemDTO cartItem);
    @PostMapping("/api/orders")
    OrderResponseDTO createOrder(@RequestBody OrderRequestDTO orderRequestDTO);
    @GetMapping("/api/orders/user/{userId}")
    List<OrderResponseDTO> getUserOrders(@PathVariable Long userId);
}

