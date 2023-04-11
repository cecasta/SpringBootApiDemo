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
    private Product _productnew;

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
    @DisplayName("get product")
    class GetProduct {

        @Test
        @DisplayName("it should return something with id 1")
        void itShouldReturnSomethingWithId() {

            // Given
            given(repository.save(_product)).willReturn(productBD);

            // When
            productService.getProduct(1L);

            // Then
            then(repository).should().equals(_product);
        }
    }

    @Nested
    @DisplayName("delete product")
    class DeleteProduct {

        @Test
        @DisplayName("after delete, it shouldn´t return something with id 1 ")
        void itShouldntReturnSomethingWithId() {

            // Given
            given(repository.save(_product)).willReturn(productBD);

            // When
            productService.deleteProduct(1L);

            // Then
            then(repository).should().deleteById(1L);
        }
    }

    @Nested
    @DisplayName("update product")
    class UpdateProduct {

        @Test
        @DisplayName("it should return something updated")
        void itShouldReturnUpdated() {

            // Given
            given(repository.save(_product)).willReturn(productBD);

            // When
            productService.updateProduct(_productnew);

            // Then
            then(repository).should().save(_productnew);
        }
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
        _productnew = Product.builder()
                .id(1)
                .name("Coca cola 3l")
                .description("Coca cola 3l")
                .type("Liquidos")
                .amount(100)
                .unitPrice(5000)
                .barCode("KHYRTYTYYYYYYY")
                .build();
    }
}