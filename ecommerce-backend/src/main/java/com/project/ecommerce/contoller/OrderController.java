package com.project.ecommerce.contoller;

import com.project.ecommerce.model.Order;
import com.project.ecommerce.repository.CartItemRepo;
import com.project.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderService service;
    @PostMapping("/orders")
    ResponseEntity<?> creatingOrder(){
        service.creatingOrder();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/orders")
    ResponseEntity<List<Order>> getOrdersData(){
        List<Order> orderList = service.getOrdersData();

        return new ResponseEntity<>(orderList,HttpStatus.OK);
    }

    @GetMapping("/orders/{orderId}")
    ResponseEntity<?> getOrderDataToTracking(@PathVariable String orderId){
        Order orderDataToTrack = service.getOrderDataToTrack(orderId);
        return new ResponseEntity<>(orderDataToTrack,HttpStatus.OK);
    }
}
