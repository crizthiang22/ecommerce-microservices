package com.example.cristhiang.stockservice.stockDTO.Response;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockResponseDTO {
    private long id;
    private long productId;
    private int quantity;
    private int reservedQuantity;
    private int availableQuantity;
    
}
