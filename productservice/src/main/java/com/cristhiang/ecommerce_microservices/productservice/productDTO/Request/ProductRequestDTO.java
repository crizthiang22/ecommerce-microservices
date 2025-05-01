package com.cristhiang.ecommerce_microservices.productservice.productDTO.Request;

import lombok.*;
import jakarta.validation.constraints.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product size must be 2-100 characters")
    private String productName;

    @NotBlank(message = "Product model is required")
    private String productModel;

    @Positive(message = "price should be a positive number")
    private float productPrice;

    @Size(max = 350, message = "Description cannot exceed 350 characters")
    private String productDescription;
}
