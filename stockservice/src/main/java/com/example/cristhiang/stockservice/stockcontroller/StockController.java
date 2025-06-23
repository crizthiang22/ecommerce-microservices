package com.example.cristhiang.stockservice.stockcontroller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cristhiang.stockservice.stockDTO.Request.StockRequestDTO;
import com.example.cristhiang.stockservice.stockDTO.Response.StockResponseDTO;
import com.example.cristhiang.stockservice.stockservice.StockService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/stock")
@Validated
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @GetMapping("/{id}")
    public StockResponseDTO getStockById(@PathVariable Long id) { 
        return stockService.findStockById(id);
    }


    @PostMapping
    public StockResponseDTO createStock(@Valid @RequestBody StockRequestDTO stockRequestDTO){
        return stockService.createStock(stockRequestDTO);
    }

    @PutMapping("/{id}")
    public StockResponseDTO updateStock(@PathVariable ("id") Long id, @Valid @RequestBody StockRequestDTO stockRequestDTO) {
        return stockService.updateStock(id, stockRequestDTO);
    }



}
