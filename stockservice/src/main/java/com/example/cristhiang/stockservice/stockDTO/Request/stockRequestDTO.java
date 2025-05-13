package com.example.cristhiang.stockservice.stockDTO.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockRequestDTO {
    @NotNull(message = "Product ID cannot be null")
    @Positive(message = "Product ID must be positive")
    private Long productId;

    @NotNull(message = "Quantity cannot be null")
    @PositiveOrZero(message = "Quantity must be positive or zero")
    private int quantity;

    @NotNull(message = "Reserved quantity cannot be null")
    @PositiveOrZero(message = "Reserved quantity must be positive or zero")
    private int reservedQuantity;
}
