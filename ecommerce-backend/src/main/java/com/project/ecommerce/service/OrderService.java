package com.project.ecommerce.service;

import com.project.ecommerce.model.*;
import com.project.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    CartItemRepo cartRepo;
    @Autowired
    PaymentSummaryService payment;
    @Autowired
    DeliveryOptionsRepo deliveryRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    OrderRepo repo;
    @Autowired
    OrderItemsRepo orderItemsRepo;
    public void creatingOrder() {

        List<OrderItems> products = new ArrayList<>();
        Order order = new Order();

        String orderId = UUID.randomUUID().toString();
        List<CartItem> cartItems = cartRepo.findAll();

        int totalCostCents = payment.getPaymentData().getTotalCostCents();

        long orderTimeMs = System.currentTimeMillis();

        order.setId(orderId);
        order.setOrderTimeMs(orderTimeMs);
        order.setTotalCostCents(totalCostCents);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        for (CartItem item : cartItems) {

            OrderItems orderItem = new OrderItems();

            orderItem.setOrderId(orderId);
            orderItem.setProductId(item.getProductId());
            orderItem.setQuantity(item.getQuantity());

            String deliveryId = item.getDeliveryOptionId();

            DeliveryOptions requiredOption =
                    deliveryRepo.findById(deliveryId).orElse(null);

            long estimatedDeliveryTimeMs =
                    orderTimeMs
                            + (long) requiredOption.getDeliveryDays()
                            * 24 * 60 * 60 * 1000;

            orderItem.setEstimatedDeliveryTimeMs(estimatedDeliveryTimeMs);

            // Save OrderItem in database
            orderItemsRepo.save(orderItem);

            // Add product information for frontend response
            Product requiredProduct =
                    productRepo.findById(item.getProductId()).orElse(null);

            orderItem.setProduct(requiredProduct);

            products.add(orderItem);
        }

        order.setProducts(products);

        // Save Order
        repo.save(order);
        cartRepo.deleteAll();
    }

    public List<Order> getOrdersData() {

        List<Order> orders = repo.findAll();
        List<OrderItems> orderItemsList = orderItemsRepo.findAll();
        List<Product> productList = productRepo.findAll();

        Map<String, List<OrderItems>> orderItemsMap = new HashMap<>();
        Map<String, Product> productsMap = new HashMap<>();

        for(Product product : productList){
            productsMap.put(product.getId(), product);
        }

        // Group OrderItems based on orderId
        for (OrderItems item : orderItemsList) {

            Product product = productsMap.get(item.getProductId());
            item.setProduct(product);
            String orderId = item.getOrderId();

            if (!orderItemsMap.containsKey(orderId)) {
                orderItemsMap.put(orderId, new ArrayList<>());
            }

            orderItemsMap.get(orderId).add(item);
        }

        // Add OrderItems to their respective Order
        for (Order order : orders) {

            String orderId = order.getId();

            List<OrderItems> products = orderItemsMap.get(orderId);

            if (products == null) {
                products = new ArrayList<>();
            }

            order.setProducts(products);
        }

        return orders;
    }

    public Order getOrderDataToTrack(String orderId) {
        Order order = repo.findById(orderId).orElse(null);
        if (order == null) {
            return null;
        }
        List<OrderItems> orderItemsList = orderItemsRepo.findAll();
        List<OrderItems> products = new ArrayList<>();
        for(OrderItems orderItem : orderItemsList){
            if(order.getId().equals(orderItem.getOrderId())){
                Product product = productRepo.findById(orderItem.getProductId()).orElse(null);
                orderItem.setProduct(product);
                products.add(orderItem);
            }
        }
        order.setProducts(products);
        return order;
    }
}
