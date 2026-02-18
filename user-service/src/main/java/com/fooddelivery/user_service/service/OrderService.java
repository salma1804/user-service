package com.fooddelivery.user_service.service;

import com.fooddelivery.user_service.dto.CartItemDTO;
import com.fooddelivery.user_service.dto.OrderRequestDTO;
import com.fooddelivery.user_service.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO);
    List<OrderResponseDTO> getUserOrderHistory(Long userId);

    CartItemDTO addToCart(Long userId,CartItemDTO cartItem);
}
