package com.example.grocery_be.dtos;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CategoryResponse {

    @Id
    private String id;

    private String name;

    private String description;
}
