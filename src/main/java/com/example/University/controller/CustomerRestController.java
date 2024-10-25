package com.example.University.controller;

import com.example.University.entity.Customer;
import com.example.University.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerRestController {

  private final CustomerService customerService;

  @PostMapping("api/v1/customer-info")
  public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    Customer savedCustomer = customerService.saveCustomer(customer);
    return ResponseEntity.ok(savedCustomer);
  }


}
