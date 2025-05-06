package com.cristhiang.ecommerce_microservices.productservice.customExceptions;


public class InvalidProductDataException extends RuntimeException{
    public InvalidProductDataException (String message){
        super(message);
    }

}
