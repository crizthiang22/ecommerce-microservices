package com.example.cristhiang.stockservice.customExceptions;

public class InsufficientStockException extends StockException {
    public InsufficientStockException(Long productId, int requested, int available) {
        super(String.format("Insufficient stock for product ID: %d. Requested: %d, Available: %d", 
            productId, requested, available));
    }
} 