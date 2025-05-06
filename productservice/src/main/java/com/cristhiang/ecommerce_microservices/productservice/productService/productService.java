package com.cristhiang.ecommerce_microservices.productservice.productService;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.cristhiang.ecommerce_microservices.productservice.productDTO.Response.ProductResponseDTO;
import com.cristhiang.ecommerce_microservices.productservice.productDTO.Request.ProductRequestDTO;
import com.cristhiang.ecommerce_microservices.productservice.customExceptions.ProductAlreadyExistException;
import com.cristhiang.ecommerce_microservices.productservice.customExceptions.ProductNotFoundException;
import com.cristhiang.ecommerce_microservices.productservice.customExceptions.ProductUpdateException;
import com.cristhiang.ecommerce_microservices.productservice.customExceptions.ProductDeleteException;
import com.cristhiang.ecommerce_microservices.productservice.customExceptions.InvalidPriceException;
import com.cristhiang.ecommerce_microservices.productservice.customExceptions.InvalidProductDataException;
import com.cristhiang.ecommerce_microservices.productservice.productMapper.ProductMapper;
import com.cristhiang.ecommerce_microservices.productservice.productRepository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

import com.cristhiang.ecommerce_microservices.productservice.productModel.Product;

@Service
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO productRequest) {
        validateProductData(productRequest);
        validateProductUniqueness(productRequest.getProductName());
        Product product = productMapper.mapToProduct(productRequest);
        Product savedProduct = productRepository.save(product);
        return productMapper.mapToProductResponseDTO(savedProduct);
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO findProductById(Long productId) {
        Product product = findProductOrThrow(productId);
        return productMapper.mapToProductResponseDTO(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::mapToProductResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO updateRequest) {
        validateProductData(updateRequest);
        Product existingProduct = findProductOrThrow(productId);
        
        if (!existingProduct.getProductName().equals(updateRequest.getProductName())) {
            validateProductUniqueness(updateRequest.getProductName());
        }

        try {
            productMapper.updateProductFromDTO(existingProduct, updateRequest);
            Product updatedProduct = productRepository.save(existingProduct);
            return productMapper.mapToProductResponseDTO(updatedProduct);
        } catch (Exception e) {
            throw new ProductUpdateException("Failed to update product: " + e.getMessage());
        }
    }

    @Transactional
    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
        
        try {
            productRepository.deleteById(productId);
        } catch (Exception e) {
            throw new ProductDeleteException("Failed to delete product: " + e.getMessage());
        }
    }

    //Helper methods 
    private void validateProductUniqueness(String productName) {
        if (productRepository.existsByProductName(productName)) {
            throw new ProductAlreadyExistException("This product already exists: " + productName);
        }
    }

    private Product findProductOrThrow(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product is not found with ID: " + productId));
    }

    private void validateProductData(ProductRequestDTO productRequest) {
        if (productRequest.getProductName() == null || productRequest.getProductName().trim().isEmpty()) {
            throw new InvalidProductDataException("Product name cannot be empty");
        }

        if (productRequest.getProductModel() == null || productRequest.getProductModel().trim().isEmpty()) {
            throw new InvalidProductDataException("Product model cannot be empty");
        }

        if (productRequest.getProductPrice() <= 0) {
            throw new InvalidPriceException("Product price must be greater than zero");
        }

        if (productRequest.getProductDescription() != null && productRequest.getProductDescription().length() > 350) {
            throw new InvalidProductDataException("Product description cannot exceed 350 characters");
        }
    }
}
