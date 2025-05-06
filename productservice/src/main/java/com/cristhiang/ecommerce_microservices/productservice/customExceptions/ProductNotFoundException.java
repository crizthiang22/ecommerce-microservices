package com.cristhiang.ecommerce_microservices.productservice.customExceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message){
        super(message);
    }

}
