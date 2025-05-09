package com.example.cristhiang.stockservice.stockrepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cristhiang.stockservice.stockmodel.Stock;

public interface stockRepository extends JpaRepository <Stock, Long> {
    List<Stock> findByStatus(String Status);
    Optional<Stock> findByProductId(String productId);
}
