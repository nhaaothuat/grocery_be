package com.example.grocery_be.dtos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;
@Data
public class ProductResponse {

    @Id
    private String id;


    private String name;

    private String description;

    private long price;

    private long offerPrice;

    private List<String> images;

    private List<String> categoryId = new ArrayList<>();

    private boolean inStock;
}
