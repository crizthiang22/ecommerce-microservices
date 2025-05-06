package com.cristhiang.ecommerce_microservices.productservice.customExceptions;

public class ProductDeleteException extends RuntimeException {
    public ProductDeleteException(String message) {
        super(message);
    }
} 