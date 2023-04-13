package com.demospring.demospring.controllers;

import com.demospring.demospring.models.entity.Product;
import com.demospring.demospring.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;
    Product testProduct;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(WebApplicationContext wac) {


        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("CocaCola L1.5");
        testProduct.setDescription("Cocacola 1.5");
        testProduct.setType("Liquidos");
        testProduct.setAmount(100);
        testProduct.setUnitPrice(5000);
        testProduct.setBarCode("KJHAHHJAAHJAJKA");
    }


    @Nested
    @DisplayName("Controller: create product")
    class ControllerCreateProduct {

        @Test
        @DisplayName("It should return product")
        public void shouldCreateProduct_thenReturnProduct()
                throws Exception {

            when(productService.createProduct(testProduct)).thenReturn(testProduct);


            mvc.perform(post("/api/v1/product")
                            .content(objectMapper.writeValueAsString(testProduct))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name").value(testProduct.getName()));

        }


        @Test
        @DisplayName("It should return exception when create product")
        public void shouldCreateProduct_thenReturnException()
                throws Exception {

            when(productService.createProduct(testProduct)).thenThrow();//


            mvc.perform(post("/api/v1/product")
                            .content(objectMapper.writeValueAsString(testProduct))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is5xxServerError());

        }

    }

}