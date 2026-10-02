package com.project.ecommerce.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PaymentSummary {
    private int totalItems;
    private int productCostCents;
    private int shippingCostCents;
    private int totalCostBeforeTaxCents;
    private int taxCents;
    private int totalCostCents;
}
