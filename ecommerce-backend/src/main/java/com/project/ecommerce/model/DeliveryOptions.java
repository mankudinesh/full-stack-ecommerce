package com.project.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="delivery_options")
@Data
public class DeliveryOptions {
    @Id
    private String id;
    private int deliveryDays;
    private String priceCents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Transient
    private long estimatedDeliveryTimeMs;

}
