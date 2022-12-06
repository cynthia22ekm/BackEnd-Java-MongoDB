package com.example.demo.repository;

import com.example.demo.model.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductsRepository extends MongoRepository<Products, String> {
    List<Products> findByCategory(String category);
}
