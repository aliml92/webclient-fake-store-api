package com.example.demo.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Product {


    private Integer id;
    private String title;
    private Double price;
    private String category;
    private String description;
    private String image;

}
