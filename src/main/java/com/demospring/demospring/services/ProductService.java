package com.demospring.demospring.services;


import com.demospring.demospring.models.entity.Product;
import com.demospring.demospring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product createProduct(Product product){
        return productRepository.save(product);
    }


    public Optional<Product> getProduct(Long id){

        return productRepository.findById(id);
    }

    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }

    public Product updateProduct(Product product){
        return productRepository.save(product);
    }
}
