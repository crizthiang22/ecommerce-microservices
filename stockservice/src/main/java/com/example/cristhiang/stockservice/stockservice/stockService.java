package com.example.cristhiang.stockservice.stockservice;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import com.example.cristhiang.stockservice.customExceptions.InvalidStockOperationException;
import com.example.cristhiang.stockservice.customExceptions.StockNotFoundException;
import com.example.cristhiang.stockservice.stockDTO.Request.StockRequestDTO;
import com.example.cristhiang.stockservice.stockDTO.Response.StockResponseDTO;
import com.example.cristhiang.stockservice.stockMapper.StockMapper;
import com.example.cristhiang.stockservice.stockmodel.Stock;
import com.example.cristhiang.stockservice.stockrepository.StockRepository;

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

    @Transactional(readOnly = true)
    public StockResponseDTO findStockById(Long id) {
        Stock stock = findStocktOrThrow(id);
        return stockMapper.mapToStockResponse(stock);
    }

    @Transactional(readOnly = true)
    public List<StockResponseDTO> getStockByProductId(Long productId) {
        return stockRepository.findByProductId(productId).stream()
            .map(stockMapper::mapToStockResponse)
            .collect(Collectors.toList());
    }
    
    @Transactional
    public StockResponseDTO updateStock(Long id, StockRequestDTO stockRequest) {
        validateStockData(stockRequest);
        Stock updatedStock = stockMapper.mapToStock(stockRequest);
        updatedStock.setId(id);
        Stock savedStock = stockRepository.save(updatedStock);
        return stockMapper.mapToStockResponse(savedStock);
    }

    @Transactional
    public void deleteStock(Long id) {
        if(!stockRepository.existsById(id)) {
            throw new StockNotFoundException(id);
        } 
        stockRepository.deleteById(id);
    }


    //Helpers 

    private void validateStockData(StockRequestDTO stockRequest){
        if (stockRequest.getProductId() == null  || stockRequest.getQuantity() <= 0) {
            throw new InvalidStockOperationException("Stock can't be created without a product ID and a price of 0 or less");
            
        }
    }


    private Stock findStocktOrThrow(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException(id));
    }
}
