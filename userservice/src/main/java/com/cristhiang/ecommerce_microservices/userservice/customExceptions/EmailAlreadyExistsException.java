package com.cristhiang.ecommerce_microservices.userservice.customExceptions;

    public class EmailAlreadyExistsException extends RuntimeException {
        public EmailAlreadyExistsException(String message){
            super(message);
        }
    }

