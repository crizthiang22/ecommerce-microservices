package com.example.cristhiang.stockservice.stockmodel;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "Stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false, unique = true) 
    private Long productId;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "reserverd_quantity", nullable = false,
    columnDefinition = "INT DEFAULT 0")
    private int reservedQuantity;

    @Transient //
    public int getAvailableQuantity() {
        return this.quantity - this.reservedQuantity;
    }

    @Transient 
    public StockStatus getStatus() {
        if (getAvailableQuantity() > 0) { 
            return StockStatus.IN_STOCK;
        } else {
            return StockStatus.OUT_OF_STOCK;
        }

        }
    }