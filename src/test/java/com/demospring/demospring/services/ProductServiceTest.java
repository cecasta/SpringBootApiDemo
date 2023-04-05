package com.demospring.demospring.services;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.demospring.demospring.models.entity.Product;
import com.demospring.demospring.repositories.ProductRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockitoAnnotations;

class ProductServiceTest {
    private AutoCloseable closeable;
    @Mock
    private ProductRepository repository;
    @InjectMocks
    private ProductService productService;
    private Product _product;
    private Product productBD;

    @BeforeEach
    void setUp() {
        buildObjects();
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }


    @Nested
    @DisplayName("create product")
    class CreateProduct {

        @Test
        @DisplayName("it should return something if happy path")
        void itShouldReturnSomethingIfHappyPath() {

            // Given
            given(repository.save(_product)).willReturn(productBD);

            // When
            productService.createProduct(_product);

            // Then
            then(repository).should().save(_product);
        }
    }


    private void buildObjects() {
        _product = Product.builder()
                .name("CocaCola L1.5")
                .description("Cocacola 1.5")
                .type("Liquidos")
                .amount(100)
                .unitPrice(5000)
                .barCode("KHYRTYTYYYYYYY")
                .build();
        productBD = Product.builder()
                .id(1)
                .name("CocaCola L1.5")
                .description("Cocacola 1.5")
                .type("Liquidos")
                .amount(100)
                .unitPrice(5000)
                .barCode("KHYRTYTYYYYYYY")
                .build();
    }
}