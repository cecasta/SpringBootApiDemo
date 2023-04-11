package com.demospring.demospring.controllers;

import com.demospring.demospring.models.entity.Product;
import com.demospring.demospring.services.ProductService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest {


    private MockMvc mvc;

    @MockBean
    private ProductService productService;
    Product testProduct;

    @BeforeEach
    void setUp(WebApplicationContext wac) {
        // instance mvc
        this.mvc = MockMvcBuilders.standaloneSetup(new ProductControllerTest()).build();
        this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();


        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("CocaCola L1.5");
        testProduct.setDescription("Cocacola 1.5");
        testProduct.setType("Liquidos");
        testProduct.setAmount(100);
        testProduct.setUnitPrice(5000);
        testProduct.setBarCode("KJHAHHJAAHJAJKA");
    }

    @Test
    @DisplayName("It should return product")
    @Disabled
    public void givenProduct_whenCreateProduct_thenReturnProduct()
            throws Exception {
        // Given

        //given(productService.createProduct(testProduct)).willReturn(testProduct);


        MvcResult result = this.mvc.perform(post("/test/simple/post")
                        .content(testProduct.toString())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        Assertions.assertEquals("{\"id\":1}", result.getResponse().getContentAsString());
    }

}