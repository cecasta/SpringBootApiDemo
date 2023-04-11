package com.demospring.demospring.models.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {


    Product testProduct;
    @BeforeEach
    void setUp() {
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
    @DisplayName("it should create object Product if happy path")
    public void itShouldCreateObjectProduct(){
        // Given
        Product _product = new Product();
        _product.setId(1L);
        _product.setName("CocaCola L1.5");
        _product.setDescription("Cocacola 1.5");
        _product.setType("Liquidos");
        _product.setAmount(100);
        _product.setUnitPrice(5000);
        _product.setBarCode("KJHAHHJAAHJAJKA");

        // then
        // When
        assertNotNull(_product.getId());
        assertNotNull(_product.getName());
        assertNotNull(_product.getDescription());
        assertNotNull(_product.getType());
        assertNotNull(_product.getBarCode());
        assertNotNull(_product.getAmount());
        assertNotNull(_product.getUnitPrice());

        // test @Data lombok

        //reflexive: for any non-null value x, the expression x.equals(x) should return true.
        assertTrue(_product.equals(testProduct));
        //symmetric: for any non-null values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.
        assertTrue(_product.equals(testProduct) && testProduct.equals(_product));
        //consistent: for any non-null values x and y, multiple invocations of x.equals(y) should consistently return true or consistently return false, provided no information used in equals comparisons on the objects is modified.
        assertTrue(_product.equals(testProduct) && _product.equals(testProduct) && _product.equals(testProduct) && _product.equals(testProduct));
        //Null check: for any non-null value x, x.equals(null) should return false.
        assertFalse(_product.equals(null));

        assertEquals(_product, testProduct);
        assertEquals(_product.hashCode(), testProduct.hashCode());
        assertEquals(_product.toString(), testProduct.toString());


    }

}