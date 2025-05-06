package com.cristhiang.ecommerce_microservices.productservice.customExceptions;

public class InvalidPriceException extends RuntimeException {
    public InvalidPriceException (String message){
        super(message);
    }

}
