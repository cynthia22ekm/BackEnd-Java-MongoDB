package com.example.demo.controller;

import com.example.demo.model.Products;
import com.example.demo.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Products>> getProductsByCategory(@PathVariable String category)
    {
        List<Products> product =  service.getProductsByCategory(category);
        if(product == null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Products>>(product,HttpStatus.OK);
    }

    @PostMapping("/PostProducts")
    public String PostProducts(@RequestBody Products product)
    {
         return service.sendProducts(product);
    }


}
