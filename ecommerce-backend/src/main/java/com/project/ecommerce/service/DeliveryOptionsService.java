package com.project.ecommerce.service;

import com.project.ecommerce.model.CartItem;
import com.project.ecommerce.model.DeliveryOptions;
import com.project.ecommerce.repository.CartItemRepo;
import com.project.ecommerce.repository.DeliveryOptionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryOptionsService {
    @Autowired
    DeliveryOptionsRepo repo;
    public void insertDeliveryOptions(DeliveryOptions option) {
        option.setCreatedAt(LocalDateTime.now());
        option.setUpdatedAt(LocalDateTime.now());
        repo.save(option);
    }

    public List<DeliveryOptions> getDeliveryOptions() {
        List<DeliveryOptions> options = repo.findAll();
        long currentTimeMs = System.currentTimeMillis();
        options.stream().filter((option) -> {
            option.setEstimatedDeliveryTimeMs(currentTimeMs + (long)option.getDeliveryDays()*24*60*60*1000);
            return true;
        }).toList();
        return options;
    }
}
