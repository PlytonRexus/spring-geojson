package com.sandbox.sandbox.models.customer;

public class CustomerNotFoundException extends RuntimeException {

  /**
  *
  */
  private static final long serialVersionUID = 5088098128641311967L;

  public CustomerNotFoundException(String id) {
    super("Could not find customer " + id);
  }
}