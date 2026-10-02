package com.project.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
@Data
public class Order {
    @Id
    String id;
    long orderTimeMs;
    int totalCostCents;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    @Transient
    List<OrderItems> products;

}
