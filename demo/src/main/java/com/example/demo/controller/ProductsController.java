package com.example.demo.controller;

import com.example.demo.model.Products;
import com.example.demo.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Products")
public class ProductsController {

    @Autowired
    private ProductsService service;

    @GetMapping("/GetProducts")
    public List<Products> getProducts()
    {
       return service.getAllProducts();
    }

    @GetMapping("/{category}")
    public Products getProduct(@PathVariable String category)
    {
        return service.getProductsByCategory(category);
    }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
    public Products createProducts(@RequestBody Products Product)
    {
        return service.addProducts(Product);
    }
}
