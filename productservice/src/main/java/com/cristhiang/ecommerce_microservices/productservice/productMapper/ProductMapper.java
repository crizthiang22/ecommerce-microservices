package com.cristhiang.ecommerce_microservices.productservice.productMapper;

import org.springframework.stereotype.Component;

import com.cristhiang.ecommerce_microservices.productservice.productModel.Product;
import com.cristhiang.ecommerce_microservices.productservice.productDTO.Request.ProductRequestDTO;
import com.cristhiang.ecommerce_microservices.productservice.productDTO.Response.ProductResponseDTO;

@Component
public class ProductMapper {
    
    public Product mapToProduct(ProductRequestDTO request) {
        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setProductModel(request.getProductModel());
        product.setProductPrice(request.getProductPrice());
        product.setProductDescription(request.getProductDescription());
        return product;
    }

    public ProductResponseDTO mapToProductResponseDTO(Product product) {
        return ProductResponseDTO.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productModel(product.getProductModel())
                .productPrice(product.getProductPrice())
                .build();
    }

    public void updateProductFromDTO(Product existingProduct, ProductRequestDTO updateRequest) {
        if (updateRequest.getProductName() != null) {
            existingProduct.setProductName(updateRequest.getProductName());
        }
        if (updateRequest.getProductModel() != null) {
            existingProduct.setProductModel(updateRequest.getProductModel());
        }
        if (updateRequest.getProductPrice() > 0) {
            existingProduct.setProductPrice(updateRequest.getProductPrice());
        }
        if (updateRequest.getProductDescription() != null) {
            existingProduct.setProductDescription(updateRequest.getProductDescription());
        }
    }
}
