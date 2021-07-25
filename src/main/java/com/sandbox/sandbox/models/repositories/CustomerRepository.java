package com.sandbox.sandbox.models.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.sandbox.sandbox.models.customer.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);
}