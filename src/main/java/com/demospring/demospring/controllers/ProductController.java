package com.demospring.demospring.controllers;

import com.demospring.demospring.models.entity.Product;
import com.demospring.demospring.repositories.ProductRepository;
import com.demospring.demospring.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Operation(summary = "Crear productos de la tienda",
            description = "Este metedo crea productos para la tienda",
            tags = { "Product"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created - Crear producto",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = " Page Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = " Internal server error - Llamar soporte", content = @Content)})
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
    @Operation(summary = "Lista un producto de la tienda",
            description = "Este metedo lista un producto de la tienda recibiendo el id del producto",
            tags = { "Product"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success - Lista producto",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = " Page Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = " Internal server error - Llamar soporte", content = @Content)})
    public ResponseEntity<Product> list(@PathVariable("id") long id) {
        Optional<Product> _product = productService.getProduct(id);
        if (_product.isPresent()) {
            return new ResponseEntity<>(_product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteproduct(@PathVariable("id") long id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        var _productupdated = productService.getProduct(id);
        if (_productupdated.isPresent()) {
            Product newupdatedproduct = _productupdated.get();
            newupdatedproduct.setName(product.getName());
            newupdatedproduct.setAmount(product.getAmount());
            newupdatedproduct.setDescription(product.getDescription());
            newupdatedproduct.setType(product.getType());
            newupdatedproduct.setBarCode(product.getBarCode());
            newupdatedproduct.setUnitPrice(product.getUnitPrice());
            return new ResponseEntity<>(productService.updateProduct(newupdatedproduct), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}

