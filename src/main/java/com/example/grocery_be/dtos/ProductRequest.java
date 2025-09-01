package com.example.grocery_be.dtos;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;
@Data
public class ProductRequest {

    private String name;

    private String description;

    private Long price;

    private Long offerPrice;


    private List<String> categoryId = new ArrayList<>();

    private boolean inStock;

}
