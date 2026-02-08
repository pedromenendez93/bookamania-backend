package com.bookamania.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100.0f);
    }

    @Test
    @DisplayName("Test get id")
    void givenAProduct_whenGetId_thenReturnId() {
        assertEquals(1L, product.getId());
    }

    @Test
    @DisplayName("Test get name")
    void givenAProduct_whenGetName_thenReturnName() {
        assertEquals("Test Product", product.getName());
    }

    @Test
    @DisplayName("Test get price")
    void givenAProduct_whenGetPrice_thenReturnPrice() {
        assertEquals(100.0f, product.getPrice());
    }

}