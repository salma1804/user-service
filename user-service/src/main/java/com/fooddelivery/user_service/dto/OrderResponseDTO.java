package com.fooddelivery.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {

    private Long orderId;
    private String status;   // CREATED, PROCESSING, COMPLETED
    private Double totalPrice;
}
