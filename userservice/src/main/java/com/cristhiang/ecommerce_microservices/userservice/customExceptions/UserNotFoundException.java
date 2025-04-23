package com.cristhiang.ecommerce_microservices.userservice.customExceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException (String message){
        super(message);
    }

}
