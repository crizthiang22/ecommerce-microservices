package com.cristhiang.ecommerce_microservices.productservice.productDTO.Response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProductResponseDTO {
    private long productId;
    private String productName;
    private String productModel;
    private float productPrice;

}
