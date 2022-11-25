package com.example.demo.controller;

import com.example.demo.model.Products;
import com.example.demo.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Products")
public class ProductsController {

    @Autowired
    private ProductsService service;

    @GetMapping
    public List<Products> getProducts()
    {
       return service.getAllProducts();
    }

    @GetMapping("/{category}")
    public Products getProduct(@PathVariable String category)
    {
        return service.getProductsByCategory(category);
    }
}
