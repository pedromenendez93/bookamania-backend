package com.pmenendez.controller;

import com.pmenendez.model.Product;
import com.pmenendez.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Tag(name = "Product", description = "Products API")
@RestController
@RequestMapping("/api/public/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @Operation(summary = "Get all products")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Products found")
    })
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @Operation(summary = "Get product by id")
    @ApiResponses(value =
        {
            @ApiResponse(responseCode = "200", description = "Product found", content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "404", description = "Product not found")
        })
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @Operation(summary = "Save a product")
    @ApiResponses(value =
        {
            @ApiResponse(responseCode = "200", description = "Product saved", content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Product.class))})
        })
    @PostMapping
    public Product saveProduct(Product product) {
        return productService.save(product);
    }

    @Operation(summary = "Update a product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product updated", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Product.class))}),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, Product product) {
        return productService.update(id, product);
    }

    @Operation(summary = "Delete a product")
    @ApiResponse(responseCode = "204", description = "Product deleted")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
