package com.sandbox.sandbox.models.customer;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.sandbox.sandbox.CustomersController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {

    @Override
    public EntityModel<Customer> toModel(Customer customer) {
        return EntityModel.of(customer, linkTo(methodOn(CustomersController.class).one(customer.getId())).withSelfRel(),
        linkTo(methodOn(CustomersController.class).showCustomers("", "")).withRel("customers"));
    }
    
}
