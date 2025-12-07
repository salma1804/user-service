package com.fooddelivery.user_service.service;

import com.fooddelivery.user_service.client.OrderClient;
import com.fooddelivery.user_service.dto.OrderRequestDTO;
import com.fooddelivery.user_service.dto.OrderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderClient orderClient;

    @Override
    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO) {
        return orderClient.createOrder(orderRequestDTO);
    }

    @Override
    public List<OrderResponseDTO> getUserOrderHistory(Long userId) {
        return orderClient.getUserOrders(userId);
    }
}
