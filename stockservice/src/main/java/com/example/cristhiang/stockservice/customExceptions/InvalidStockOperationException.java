package com.example.cristhiang.stockservice.customExceptions;

public class InvalidStockOperationException extends StockException {
    public InvalidStockOperationException(String message) {
        super(message);
    }

    public InvalidStockOperationException(String message, Throwable cause) {
        super(message, cause);
    }
} 