package com.example.cristhiang.stockservice.stockMapper;

import org.springframework.stereotype.Component;

import com.example.cristhiang.stockservice.stockDTO.Request.StockRequestDTO;
import com.example.cristhiang.stockservice.stockDTO.Response.StockResponseDTO;
import com.example.cristhiang.stockservice.stockmodel.Stock;

@Component
public class stockMapper {

    public Stock mapToStock(StockRequestDTO request) {
        Stock stock = new Stock();
        stock.setProductId(request.getProductId());
        stock.setQuantity(request.getQuantity());
        stock.setReservedQuantity(request.getReservedQuantity());
        return stock;
    }

    public StockResponseDTO mapToStockResponse(Stock stock) {
        return StockResponseDTO.builder()
                .id(stock.getId())
                .productId(stock.getProductId())
                .quantity(stock.getQuantity())
                .reservedQuantity(stock.getReservedQuantity())
                .availableQuantity(stock.getAvailableQuantity())
                .build();
    }

    public void updateStockFromRequest(StockRequestDTO request, Stock stock) {
        if (request.getQuantity() >= 0) {
            stock.setQuantity(request.getQuantity());
        }
        
        if (request.getReservedQuantity() >= 0) {
            stock.setReservedQuantity(request.getReservedQuantity());
        }
        
    }
}
