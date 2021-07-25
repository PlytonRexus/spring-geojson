package com.sandbox.sandbox;

import java.util.List;

import com.sandbox.sandbox.dtos.order.OrderDTO;
import com.sandbox.sandbox.models.order.Order;
import com.sandbox.sandbox.models.repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {
    @Autowired
    private OrderRepository repository;
    private static final String DEFAULT_VALUE = "original";

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(
            @RequestParam(value = "productId", defaultValue = DEFAULT_VALUE) String productId,
            @RequestParam(value = "customerId", defaultValue = DEFAULT_VALUE) String customerId) {
        if (!productId.equals(DEFAULT_VALUE) && !customerId.equals(DEFAULT_VALUE)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(repository.findByProductIdAndCustomerId(productId, customerId));
        } else if (!productId.equals(DEFAULT_VALUE)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.findByProductId(productId));
        } else if (!customerId.equals(DEFAULT_VALUE)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.findByCustomerId(customerId));
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.findAll());
    }

    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable String id) {
        var tmp = repository.findById(id);
        if (tmp.isPresent()) {
            return tmp.get();
        }
        return null;
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> persistCustomer(@RequestBody OrderDTO tmp) {
        if (tmp == null) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new Order("Something's wrong with that request."));
        } else {
            Order customer = new Order(tmp);
            repository.save(customer);
            return ResponseEntity
                .status(HttpStatus.CREATED).body(customer);
        }
    }
}
