package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Products")
public class Products {

    @Id
    private String title;
    private int price;
    private String description;
    private String category;
    private Rating rating;

}
