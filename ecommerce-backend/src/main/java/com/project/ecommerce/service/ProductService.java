package com.project.ecommerce.service;

import com.project.ecommerce.model.Product;
import com.project.ecommerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class ProductService {
    @Autowired
    ProductRepo repo;
    public void addProducts(List<Product> products) {
        repo.saveAll(products);
    }

    public List<Product> getProductsToHome() {
        return repo.findAll();
    }
}
