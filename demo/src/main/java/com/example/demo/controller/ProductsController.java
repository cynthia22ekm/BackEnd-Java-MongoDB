package com.example.demo.controller;
import com.example.demo.model.Products;
import com.example.demo.service.PostProductResponse;
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


    @RequestMapping(value="/GetProducts", method= RequestMethod.GET)
    public List<Products> getProducts()
    {
       return service.getAllProducts();
    }

    @RequestMapping(value="/{category}", method= RequestMethod.GET)
    public ResponseEntity<List<Products>> getProductsByCategory(@PathVariable String category)
    {
        List<Products> product =  service.getProductsByCategory(category);
        if(product == null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Products>>(product,HttpStatus.OK);
    }

    @RequestMapping(value="/SendProducts", method= RequestMethod.POST)
    public PostProductResponse SendProducts(@RequestBody Products product)
    {
        return service.sendProducts(product);
    }


    @RequestMapping(value="/UpdateProducts", method= RequestMethod.PUT)
    public PostProductResponse UpdateProducts(@RequestBody Products product)
    {
         return service.updateProducts(product);
    }

    @RequestMapping(value="/deleteProduct/{id}", method= RequestMethod.DELETE)
    public String deleteProductById(@PathVariable String id)
    {
        return service.deleteProducts(id);
    }

}
