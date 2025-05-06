package com.cristhiang.ecommerce_microservices.productservice.customExceptions;

public class ProductAlreadyExistException extends RuntimeException {
    public ProductAlreadyExistException(String message){
        super(message);
    }

}
