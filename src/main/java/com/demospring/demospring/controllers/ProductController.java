package com.demospring.demospring.controllers;

import com.demospring.demospring.models.entity.Product;
import com.demospring.demospring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping(produces="application/json")
    public ResponseEntity<Product> create(@RequestBody Product product){
        try {
            var _product = productService.createProduct(product);
            return new ResponseEntity<>(_product, HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> list(@PathVariable("id") long id){
        Optional<Product> _product = productService.getProduct(id);
        if (_product.isPresent()) {
            return new ResponseEntity<>(_product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping
    public void delete(){}


    @PutMapping
    public void update(){}
}
