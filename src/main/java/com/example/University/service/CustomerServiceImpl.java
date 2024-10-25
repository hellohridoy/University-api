package com.example.University.service;

import com.example.University.entity.Customer;
import com.example.University.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Override
  public Customer saveCustomer(Customer customer) {
    return customerRepository.save(customer);
  }
}
