package com.example.demo.controller;

import com.example.demo.dto.req.ProductForm;
import com.example.demo.dto.res.Category;
import com.example.demo.dto.res.Product;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/products")
public class SearchController {

    // create a web client object for product-related http calls
    WebClient client = WebClient.create("https://fakestoreapi.com/products");


    // get all products (20), or limited number of products or by sorting or by mixing limit and sort
    @GetMapping
    public Flux<Product> getAll(@RequestParam(value = "limit", required = false) Integer limit,
                                @RequestParam(value = "sort", required = false) String sort){
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/").queryParam("limit", limit).queryParam("sort", sort).build())
                .retrieve()
                .bodyToFlux(Product.class);

    }

    // get single product by id
    @GetMapping("/{id}")
    public Mono<Product> getById(@PathVariable Integer id){
        return client.get()
                .uri("/" + id)
                .retrieve()
                .bodyToMono(Product.class);

    }

    // get all categories
    @GetMapping("/categories")
    public Flux<List> getAllCategories(){
        return client.get()
                .uri("/categories" )
                .retrieve()
                .bodyToFlux(List.class);

    }

    // get all products by category
    @GetMapping("/category/{category}")
    public Flux<Product> getAllCategories(@PathVariable String category){
        return client.get()
                .uri("/category/" + category )
                .retrieve()
                .bodyToFlux(Product.class);

    }

    // create a new product
    @PostMapping
    public Mono<Product> createNew(@RequestBody ProductForm form){
        return client.post()
                .uri("")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(form), Product.class)
                .retrieve()
                .bodyToMono(Product.class);
    }


    @PutMapping("/{id}")
    public Mono<Product> updateByID(@PathVariable Integer id, @RequestBody ProductForm form){
        return client.put()
                .uri("/" + id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(form), Product.class)
                .retrieve()
                .bodyToMono(Product.class);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable Integer id) {
        return client.delete()
                .uri("/" +id)
                .retrieve()
                .bodyToMono(Void.class);
    }


}
