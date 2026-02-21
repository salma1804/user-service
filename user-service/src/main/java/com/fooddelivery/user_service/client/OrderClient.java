package com.fooddelivery.user_service.client;

import com.fooddelivery.user_service.dto.CartItemDTO;
import com.fooddelivery.user_service.dto.OrderRequestDTO;
import com.fooddelivery.user_service.dto.OrderResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "order-service",
        url = "http://localhost:8085",
        configuration = com.fooddelivery.user_service.config.FeignClientConfig.class
)
public interface OrderClient {

    // -------------------------
    // CART METHODS
    // -------------------------

    @PostMapping("/api/cart/add")
    CartItemDTO addToCart(@RequestBody CartItemDTO cartItem,
                          @RequestParam("userId") Long userId);

    @GetMapping("/api/cart/{userId}")
    List<CartItemDTO> getCart(@PathVariable("userId") Long userId);

    @DeleteMapping("/api/cart/{userId}")
    void clearCart(@PathVariable("userId") Long userId);

    // -------------------------
    // ORDER METHODS
    // -------------------------

    @PostMapping("/orders")
    OrderResponseDTO createOrder(@RequestBody OrderRequestDTO orderRequestDTO);

    @GetMapping("/orders/{orderId}")
    OrderResponseDTO getOrderById(@PathVariable("orderId") Long orderId);

    @GetMapping("/orders/customer/{userId}")
    List<OrderResponseDTO> getOrdersByCustomer(@PathVariable("userId") Long userId);

    // -------------------------
    // CREATE ORDER FROM CART
    // -------------------------

    @PostMapping("/orders/from-cart")
    OrderResponseDTO createOrderFromCart(@RequestParam("userId") Long userId,
                                         @RequestBody OrderRequestDTO orderRequestDTO);
}