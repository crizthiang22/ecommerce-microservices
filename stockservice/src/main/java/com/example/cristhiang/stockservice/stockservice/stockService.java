package com.example.cristhiang.stockservice.stockservice;

import org.springframework.stereotype.Service;

import com.example.cristhiang.stockservice.customExceptions.InvalidStockOperationException;
import com.example.cristhiang.stockservice.customExceptions.StockNotFoundException;
import com.example.cristhiang.stockservice.stockDTO.Request.StockRequestDTO;
import com.example.cristhiang.stockservice.stockDTO.Response.StockResponseDTO;
import com.example.cristhiang.stockservice.stockMapper.StockMapper;
import com.example.cristhiang.stockservice.stockmodel.Stock;
import com.example.cristhiang.stockservice.stockrepository.StockRepository;

import jakarta.transaction.Transactional;

@Service 
public class StockService {

    private final StockMapper stockMapper;
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository, StockMapper stockMapper){
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    @Transactional 
    public StockResponseDTO createStock (StockRequestDTO stockRequest) {
        validateStockData(stockRequest);
        Stock stock = stockMapper.mapToStock(stockRequest);
        Stock savedStock = stockRepository.save(stock);
        return stockMapper.mapToStockResponse(savedStock);
        
    }

    @Transactional
    public StockResponseDTO findStockById(Long id) {
        Stock stock = findStocktOrThrow(id);
        return stockMapper.mapToStockResponse(stock);
    }




    //Helpers 

    private void validateStockData(StockRequestDTO stockRequest){
        if (stockRequest.getProductId() == null  || stockRequest.getQuantity() <= 0) {
            throw new InvalidStockOperationException("Stock can't be created without a product ID and a price of 0 or less");
            
        }
    }


    private Stock findStocktOrThrow(Long id) {
        return StockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException("Stock for this product is not found or added yet: " + id ));
    }
}
