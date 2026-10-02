package com.project.ecommerce.model;

import lombok.Data;

@Data
public class CartItemUpdateRequest {
    private Integer quantity;
    private String deliveryOptionId;
}