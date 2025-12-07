package com.fooddelivery.user_service.service;

import com.fooddelivery.user_service.client.OrderClient;
import com.fooddelivery.user_service.dto.CartItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final OrderClient orderClient;

    @Override
    public CartItemDTO addToCart(CartItemDTO cartItem) {
        return orderClient.addToCart(cartItem);
    }
}
