package com.cristhiang.ecommerce_microservices.userservice.customExceptions;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException (String message){
        super(message);
    }

}
