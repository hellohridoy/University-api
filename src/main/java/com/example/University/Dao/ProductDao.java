package com.example.University.Dao;

import com.example.University.Dto.UpazilaDetailsDto;
import com.example.University.Dto.UpazilaResponseDto;
import com.example.University.Dto.ZilaResponseDto;
import com.example.University.entity.Customer;
import com.example.University.entity.Product;

import java.util.List;

public interface ProductDao {
  List<Product> getAll();
  Product getProductById(Long id);
}

