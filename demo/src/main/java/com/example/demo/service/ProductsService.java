package com.example.demo.service;

import com.example.demo.model.Products;
import com.example.demo.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


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
        Optional<Products> productById = repository.findById(product.getId());
        if(product.getCategory().length()==0) {
            return new PostProductsResponse("Category is mandatory", product, HttpStatus.CONFLICT);
        }
        if(productById.isPresent())
        {
            return new PostProductsResponse("Product with same ID exist in Database", product, HttpStatus.CONFLICT);
        }
        else {
            repository.save(product);
            return new PostProductsResponse("Products Created Successfully", product, HttpStatus.OK);
        }
    }


    public PostProductsResponse updateProducts(Products product) {

        Optional<Products> productById = repository.findById(product.getId());
        System.out.println("Product ID"+productById);
        if(!(productById.isPresent()))
        {
            return new PostProductsResponse("Product does not Exist in Database", product, HttpStatus.CONFLICT);
        }
        else {
            repository.save(product);
            return new PostProductsResponse("Products Updated Successfully", product, HttpStatus.OK);
        }
    }
    public String deleteProducts(String productId )
    {
        Optional<Products> productById  = repository.findById(productId);
        repository.deleteById(productId);
        return  "Product deleted successfully";
    }
}
