package com.pmenendez.service;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pmenendez.model.Product;
import com.pmenendez.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductServiceTest {

    private ProductService productService;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    @DisplayName("Given some products when find all then return all products")
    void givenProducts_whenFindAll_thenReturnAllProducts() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100.0f);

        when(productRepository.findAll()).thenReturn(singletonList(product));

        List<Product> products = productService.findAll();

        assertEquals(1, products.size());
        assertEquals(product, products.get(0));
    }

    @Test
    @DisplayName("Given a productId when findById then return the correct product")
    void givenProductId_whenFindById_thenReturnProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100.0f);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.findById(1L);

        assertTrue(foundProduct.isPresent());
        assertEquals(product, foundProduct.get());
    }

    @Test
    @DisplayName("Given a product when save then return saved product")
    void givenProduct_whenSave_thenReturnSavedProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100.0f);

        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.save(product);

        assertEquals(product, savedProduct);
    }

    @Test
    @DisplayName("Given a product to update when update operation then return the updated product")
    void givenProductIdAndProduct_whenUpdate_thenReturnUpdatedProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100.0f);

        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Product");
        updatedProduct.setPrice(200.0f);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(updatedProduct);

        Product resultProduct = productService.update(1L, updatedProduct);

        assertEquals(updatedProduct, resultProduct);
    }

    @Test
    @DisplayName("If a product does not exists when update, then throw an exception")
    void givenNonExistentProductId_whenUpdate_thenThrowException() {
        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Product");
        updatedProduct.setPrice(200.0f);

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> productService.update(1L, updatedProduct));
    }

    @Test
    @DisplayName("Given a productId when delete byId then remove the product")
    void givenProductId_whenDeleteById_thenProductIsDeleted() {
        productService.deleteById(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }
}