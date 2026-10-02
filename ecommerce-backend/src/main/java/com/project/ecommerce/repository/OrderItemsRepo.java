package com.project.ecommerce.repository;

import com.project.ecommerce.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepo extends JpaRepository<OrderItems,Integer>{

}
