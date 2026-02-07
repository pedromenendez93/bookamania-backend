package com.pmenendez.service;

import com.pmenendez.model.Product;
import com.pmenendez.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product update(Long id, Product product) {
        return productRepository.findById(id)
            .map(productToUpdate -> updateProductDetailsAndSave(product, productToUpdate))
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    private Product updateProductDetailsAndSave(Product source, Product target) {
        target.setName(source.getName());
        target.setPrice(source.getPrice());
        return productRepository.save(target);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

}
