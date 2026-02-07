package com.pmenendez.controller;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmenendez.model.Product;
import com.pmenendez.service.ProductService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class ProductControllerTest {

    private ProductService productService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        productService = mock(ProductService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductController(productService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void givenProducts_whenGetAllProducts_thenReturnAllProducts() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100.0f);

        when(productService.findAll()).thenReturn(singletonList(product));

        mockMvc.perform(get("/api/public/product"))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(singletonList(product))));
    }

    @Test
    void givenProductId_whenGetProductById_thenReturnProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100.0f);

        when(productService.findById(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/api/public/product/1"))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(product)));
    }

    @Test
    void givenProduct_whenSaveProduct_thenReturnSavedProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100.0f);

        when(productService.save(product)).thenReturn(product);

        mockMvc.perform(post("/api/public/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
            .andExpect(status().isOk());
    }

    @Test
    void givenProductIdAndProduct_whenUpdateProduct_thenReturnUpdatedProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100.0f);

        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Product");
        updatedProduct.setPrice(200.0f);

        when(productService.update(1L, updatedProduct)).thenReturn(updatedProduct);

        mockMvc.perform(put("/api/public/product/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProduct)))
            .andExpect(status().isOk());
    }

    @Test
    void givenProductId_whenDeleteProduct_thenProductIsDeleted() throws Exception {
        doNothing().when(productService).deleteById(1L);

        mockMvc.perform(delete("/api/public/product/1"))
            .andExpect(status().isOk());

        verify(productService, times(1)).deleteById(1L);
    }
}