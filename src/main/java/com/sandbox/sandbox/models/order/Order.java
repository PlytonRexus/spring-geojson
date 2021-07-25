package com.sandbox.sandbox.models.order;

import com.sandbox.sandbox.dtos.order.OrderDTO;
import com.sandbox.sandbox.dtos.order.Status;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("orders")
public class Order {
    @Id
    private String id;
    private String customerId;
    private Status status;
    private String description;
    private String productId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer() {
        return customerId;
    }

    public void setCustomer(String customerId) {
        this.customerId = customerId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Order(String customerId, Status status, String description) {
        this.customerId = customerId;
        this.status = status;
        this.description = description;
    }

    public Order(OrderDTO oDto) {
        this.status = oDto.getStatus();
        this.customerId = oDto.getCustomerId();
        this.description = oDto.getDescription();
        this.productId = oDto.getProductId();
    }

    @Override
    public String toString() {
        return "Order [customer=" + customerId 
            + ", description=" + description 
            + ", id=" + id 
            + ", status=" + status + "]";
    }

    public Order() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Order(String productId) {
        this.productId = productId;
    }

    
}
