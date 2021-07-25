package com.sandbox.sandbox.models.customer;

import com.sandbox.sandbox.dtos.customer.Characteristics;
import com.sandbox.sandbox.dtos.customer.CustomerDTO;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "customers")
public class Customer {
    @Id
    private String id;
    private String firstName = "Unnamed";
    private String lastName = "Rabbit";
    private Characteristics characteristics;

    public Customer(CustomerDTO customer) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.characteristics = customer.getCharacteristics();
    }

    public Customer(
        String firstName, String lastName, 
        Integer age, String gender, String hairColour, String eyeColour) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.characteristics = 
            new Characteristics(age, gender, hairColour, eyeColour);
    }

    public Customer(String firstName, String lastName) {
		this.firstName = firstName;
        this.lastName = lastName;
        this.characteristics = new Characteristics();
    }
    
    public Customer() {
        this.characteristics = new Characteristics();
    }

    public Customer(String string) {
        this.firstName = string;
	}

	@Override
	public String toString() {
        return "Customer [characteristics=" + characteristics.toString() 
        + ", firstName=" + firstName 
        + ", lastName=" + lastName + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Characteristics getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(Characteristics characteristics) {
		this.characteristics = characteristics;
	}
}
