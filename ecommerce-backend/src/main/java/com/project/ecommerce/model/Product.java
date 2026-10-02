package com.project.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private String image;
    private int priceCents;
    @Embedded
    private Rating rating;
    @ElementCollection
    private List<String> keywords;

}
