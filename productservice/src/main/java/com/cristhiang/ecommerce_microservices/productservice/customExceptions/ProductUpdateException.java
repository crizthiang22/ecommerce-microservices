package com.cristhiang.ecommerce_microservices.productservice.customExceptions;

public class ProductUpdateException extends RuntimeException {
    public ProductUpdateException(String message) {
        super(message);
    }
} 