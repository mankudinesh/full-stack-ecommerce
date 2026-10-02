package com.project.ecommerce.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Rating {
    double stars;
    int count;
}
