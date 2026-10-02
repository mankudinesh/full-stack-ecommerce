package com.project.ecommerce.service;

import com.project.ecommerce.model.CartItem;
import com.project.ecommerce.model.DeliveryOptions;
import com.project.ecommerce.model.PaymentSummary;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.repository.CartItemRepo;
import com.project.ecommerce.repository.DeliveryOptionsRepo;
import com.project.ecommerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentSummaryService {
    @Autowired
    CartItemRepo cartRepo;
    @Autowired
    ProductRepo prodRepo;
    @Autowired
    DeliveryOptionsRepo deliveryRepo;
    @Autowired
    PaymentSummary obj;
    public PaymentSummary getPaymentData() {
        int totalitems = 0;
        int productcostcents=0;
        int shippingcostcents=0;
        for(CartItem item : cartRepo.findAll()){
            int q = item.getQuantity();
            totalitems += q;
            Product product = prodRepo.findById(item.getProductId()).orElse(null);
            productcostcents += product.getPriceCents()*q;
            DeliveryOptions option = deliveryRepo.findById(item.getDeliveryOptionId()).orElse(null);
            shippingcostcents += Integer.parseInt(option.getPriceCents());
        }
        int totalcostbeforetaxcents = productcostcents + shippingcostcents;
        int taxcents = totalcostbeforetaxcents/10;
        int totalcostcents = totalcostbeforetaxcents + taxcents;
        obj.setTotalItems(totalitems);
        obj.setProductCostCents(productcostcents);
        obj.setShippingCostCents(shippingcostcents);
        obj.setTotalCostBeforeTaxCents(totalcostbeforetaxcents);
        obj.setTaxCents(taxcents);
        obj.setTotalCostCents(totalcostcents);
        return obj;
    }
}
