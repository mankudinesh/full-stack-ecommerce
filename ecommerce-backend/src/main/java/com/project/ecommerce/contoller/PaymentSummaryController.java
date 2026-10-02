package com.project.ecommerce.contoller;

import com.project.ecommerce.model.PaymentSummary;
import com.project.ecommerce.service.PaymentSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PaymentSummaryController {
    @Autowired
    PaymentSummaryService service;
    @GetMapping("/payment-summary")
    public ResponseEntity<PaymentSummary> getPaymentData(){

        return new ResponseEntity<>(service.getPaymentData(),HttpStatus.OK);
    }
}
