package com.example.demo.repository;

import com.example.demo.model.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsRepository extends MongoRepository<Products, Integer> {
    Products findByCategory(String category);
}
