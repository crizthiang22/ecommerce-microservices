package com.example.cristhiang.stockservice.stockmodel;

public enum StockStatus {
    IN_STOCK("In Stock"),
    OUT_OF_STOCK("Out of Stock");

    private final String displayName;

    StockStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
