package com.project.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="cart_items")
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String productId;
    private int quantity;
    private String deliveryOptionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Transient
    private Product product;
}
