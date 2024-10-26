package com.example.University.service;

import com.example.University.entity.Customer;
import com.example.University.entity.Product;

import java.util.List;

public interface ProductService {

  List<Product> getAll();

  Product getById(Long id);
}
