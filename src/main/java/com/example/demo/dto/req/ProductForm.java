package com.example.demo.dto.req;

import lombok.Data;

@Data
public class ProductForm {

    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;
}
