package com.sandbox.sandbox;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import com.sandbox.sandbox.dtos.customer.CustomerDTO;
import com.sandbox.sandbox.models.customer.Customer;
import com.sandbox.sandbox.models.customer.CustomerModelAssembler;
import com.sandbox.sandbox.models.customer.CustomerNotFoundException;
import com.sandbox.sandbox.models.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomersController {
    @Autowired
    private CustomerRepository repository;
    private CustomerModelAssembler assembler;

    @GetMapping("/customers")
    public CollectionModel<EntityModel<Customer> > showCustomers(
            @RequestParam(value = "firstName", defaultValue = "Alice") String firstName,
            @RequestParam(value = "lastName", defaultValue = "Smith") String lastName) {
        List<EntityModel<Customer> > customers = 
            repository
            .findAll()
            .stream()
            .map(customer -> assembler.toModel(customer))
            .collect(Collectors.toList());

        return CollectionModel.of(customers, 
            linkTo(methodOn(CustomersController.class).showCustomers("", ""))
                .withSelfRel()
        );
    }

    @PostMapping("/customers/create")
    public Customer createCustomers(@RequestParam(value = "firstName", defaultValue = "Alice") String firstName,
            @RequestParam(value = "lastName", defaultValue = "Smith") String lastName,
            @RequestParam(value = "eyeColour", defaultValue = "Brown") String eyeColour,
            @RequestParam(value = "hairColour", defaultValue = "Black") String hairColour,
            @RequestParam(value = "age", defaultValue = "18") int age,
            @RequestParam(value = "gender", defaultValue = "Not provided") String gender) {
        Customer newCustomer = new Customer(firstName, lastName, age, gender, hairColour, eyeColour);
        repository.save(newCustomer);
        return newCustomer;
    }

    @GetMapping("/customers/{id}")
    public EntityModel<Customer> one(@PathVariable String id) {
        Customer customer = repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        return assembler.toModel(customer);
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> persistCustomer(@RequestBody CustomerDTO tmp) {
        if (tmp == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new Customer("Something's wrong with that request."));
        } else {
            Customer customer = new Customer(tmp);
            repository.save(customer);
            return ResponseEntity
                    // .created(EntityModel.)
                    .status(HttpStatus.CREATED).body(customer);
        }
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> replaceCustomer(@RequestBody CustomerDTO tmp, @PathVariable String id) {
        return repository.findById(id).map(customer -> {
            customer = new Customer(tmp);
            customer.setId(id);
            repository.save(customer);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(customer);
        }).orElseGet(() -> {
            Customer customer = new Customer(tmp);
            customer.setId(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(customer));
        });

    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable String id) {
        Customer customer = repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        repository.delete(customer);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customer);
    }

    public CustomersController(
        CustomerRepository repository, 
        CustomerModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }
}
