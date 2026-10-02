package com.project.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Table(name="order_items")
@Data
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String orderId;
    String productId;
    int quantity;
    long estimatedDeliveryTimeMs;
    @Transient
    Product product;
    @Transient
    int priceCents;
    @Transient
    LocalDateTime createdAt;
    @Transient
    LocalDateTime updatedAt;
}
