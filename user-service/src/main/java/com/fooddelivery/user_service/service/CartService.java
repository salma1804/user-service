package com.fooddelivery.user_service.service;

import com.fooddelivery.user_service.dto.CartItemDTO;

public interface CartService {
    CartItemDTO addToCart(CartItemDTO cartItem);
}
