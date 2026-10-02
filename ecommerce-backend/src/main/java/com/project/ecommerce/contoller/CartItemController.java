package com.project.ecommerce.contoller;

import com.project.ecommerce.model.CartItem;
import com.project.ecommerce.model.CartItemUpdateRequest;
import com.project.ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CartItemController {
    @Autowired
    CartItemService service;
    @PostMapping("/cart-items")
    public ResponseEntity<?> addCartItems(@RequestBody CartItem cartItems){
        service.addCartItems(cartItems);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/cart-items")
    public ResponseEntity<List<CartItem>> getCartItems(){
        return new ResponseEntity<>(service.getCartItems(),HttpStatus.OK);
    }
    @DeleteMapping("/cart-items/{productId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable String productId){
        service.deleteCartItem(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/cart-items/{productId}")
    public void updateCartItem(@PathVariable String productId,@RequestBody CartItemUpdateRequest request){
        service.updateCartItem(productId,request);
    }
}

