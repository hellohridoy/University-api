package com.example.University.service;

import com.example.University.Dao.ProductDao;
import com.example.University.entity.Customer;
import com.example.University.entity.Product;
import com.example.University.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

  private final ProductDao productDao;

  @Override
  public List<Product> getAll() {
    return productDao.getAll();
  }

  @Override
  public Product getById(Long id) {
    return productDao.getProductById(id);
  }
}
