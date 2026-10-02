package com.project.ecommerce.contoller;

import com.project.ecommerce.model.Product;
import com.project.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService service;

    @PostMapping("/products/bulk")
    public ResponseEntity<?> addProducts(@RequestBody List<Product> products){
        service.addProducts(products);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProductsToHome(){
        List<Product> products = service.getProductsToHome();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
