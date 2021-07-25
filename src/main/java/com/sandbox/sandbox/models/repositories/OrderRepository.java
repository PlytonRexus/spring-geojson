package com.sandbox.sandbox.models.repositories;

import java.util.List;

import com.sandbox.sandbox.models.order.Order;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
    public List<Order> findByProductId(String productId);
    public List<Order> findByCustomerId(String customerId);
    public Order findByDescription(String description);
    public List<Order> findByProductIdAndCustomerId(String productId, String customerId);
}
