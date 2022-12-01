package com.example.demo.service;

import com.example.demo.model.Products;
import com.example.demo.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ProductsService {

    @Autowired
    private ProductsRepository repository;

    public List<Products> getAllProducts()
    {
        return repository.findAll();
    }

    public Products getProductsByCategory(String category)
    {
        return repository.findByCategory(category);
    }

    public Products addProducts(Products Product)
    {

        return repository.save(Product);
    }
}
