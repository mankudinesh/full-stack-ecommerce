package com.project.ecommerce.service;

import com.project.ecommerce.model.CartItem;
import com.project.ecommerce.model.CartItemUpdateRequest;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.repository.CartItemRepo;
import com.project.ecommerce.repository.ProductRepo;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Component
public class CartItemService {
    @Autowired
    CartItemRepo repo;
    @Autowired
    ProductRepo productRepo;
    public void addCartItems(CartItem cartItem) {
        List<CartItem> items = repo.findAll();
        CartItem existingItem = items.stream()
                .filter(item -> item.getProductId().equals(cartItem.getProductId()))
                .findFirst()
                .orElse(null);
        if (existingItem == null) {
            cartItem.setCreatedAt(LocalDateTime.now());
            cartItem.setUpdatedAt(LocalDateTime.now());
            cartItem.setDeliveryOptionId("1");
            existingItem = cartItem;
        }
        else{
            existingItem.setQuantity(cartItem.getQuantity() + existingItem.getQuantity());
            existingItem.setUpdatedAt(LocalDateTime.now());
        }
        repo.save(existingItem);
    }
    Product product;

    public List<CartItem> getCartItems() {

        List<CartItem> cartItems = repo.findAll();
       return cartItems.stream().map((item) -> {
            product = productRepo.findById(item.getProductId()).orElse(null);
            item.setProduct(product);
            return item;
        }).toList();
    }

    public void deleteCartItem(String productId) {
        List<CartItem> items = repo.findAll();
        CartItem requiredItem = items.stream().filter(item->item.getProductId().equals(productId))
                .findFirst().orElse(null);
        if (requiredItem != null) {
            repo.delete(requiredItem);
        }
    }

    public void updateCartItem(String productId, CartItemUpdateRequest request) {
        List<CartItem> items = repo.findAll();
        CartItem requiredItem = items.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
        if (requiredItem == null) {
            return;
        }
        if (request.getQuantity() != null) {
            requiredItem.setQuantity(request.getQuantity());
        }
        if (request.getDeliveryOptionId() != null) {
            requiredItem.setDeliveryOptionId(request.getDeliveryOptionId());
        }
        requiredItem.setUpdatedAt(LocalDateTime.now());
        repo.save(requiredItem);
    }
}


