package com.project.ecommerce.contoller;

import com.project.ecommerce.model.CartItem;
import com.project.ecommerce.model.DeliveryOptions;
import com.project.ecommerce.repository.CartItemRepo;
import com.project.ecommerce.service.DeliveryOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class DeliveryOptionsController {
    @Autowired
    DeliveryOptionsService service;
    @PostMapping("/delivery-options")
    public ResponseEntity<?> insertDeliveryOptions(@RequestBody DeliveryOptions option){
        service.insertDeliveryOptions(option);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/delivery-options")
    public ResponseEntity<?> getDeliveryOptions(){
        List<DeliveryOptions> options =  service.getDeliveryOptions();
        return new ResponseEntity<>(options,HttpStatus.OK);
    }
}
