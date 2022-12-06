package com.example.demo.service;

import com.example.demo.model.Products;
import com.example.demo.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ProductsService {

    @Autowired
    private ProductsRepository repository;

    public List<Products> getAllProducts() {
        return repository.findAll();
    }

    public List<Products> getProductsByCategory(String category) {
        return (List<Products>) repository.findByCategory(category);
    }

    public PostProductsResponse sendProducts(Products product)
    {
         repository.save(product);
        return new PostProductsResponse( "Products Created Successfully", product, HttpStatus.OK);
    }


}
