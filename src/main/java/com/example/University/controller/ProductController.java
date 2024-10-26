package com.example.University.controller;

import com.example.University.Dto.StudentInfoBySubjectsDto;
import com.example.University.Dto.StudentResultDto;
import com.example.University.entity.Product;
import com.example.University.entity.Student;
import com.example.University.service.ProductService;
import com.example.University.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/v1/product/product-infos")
    public ResponseEntity<List<Product>> getAllProducts() {
      List<Product> products = productService.getAll();
      return ResponseEntity.ok(products);  // Return the list of products with status 200 (OK)
    }

  @GetMapping("/v1/product/product-infos/{id}")
  public Product getAllProductsById(
    @PathVariable(required = false) Long id) {
    Product products = productService.getById(id);
    return products;  // Return the list of products with status 200 (OK)
  }

}
