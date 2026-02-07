package com.pmenendez.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Schema(description = "Product entity")
@Entity
@Data
public class Product {

    @Id
    @Schema(description = "Product id", example = "1")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Schema(description = "Product name", example = "Elden Ring PS5")
    private String name;
    @Schema(description = "Product price", example = "69.99")
    @Positive
    private Float price;

}
