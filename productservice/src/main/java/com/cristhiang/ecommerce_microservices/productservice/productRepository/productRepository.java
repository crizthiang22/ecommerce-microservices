package com.cristhiang.ecommerce_microservices.productservice.productRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiang.ecommerce_microservices.productservice.productModel.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long>{
    boolean existsByProductName(String productName);

}
