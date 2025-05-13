package com.example.cristhiang.stockservice.customExceptions;

public class StockNotFoundException extends StockException {
    public StockNotFoundException(Long productId) {
        super(String.format("Stock not found for product ID: %d", productId));
    }
} 