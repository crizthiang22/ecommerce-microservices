package com.cristhiang.ecommerce_microservices.productservice.productController;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristhiang.ecommerce_microservices.productservice.productDTO.Request.ProductRequestDTO;
import com.cristhiang.ecommerce_microservices.productservice.productDTO.Response.ProductResponseDTO;
import com.cristhiang.ecommerce_microservices.productservice.productService.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/products")
@Validated

public class ProductController {
    private final ProductService productService;

    public ProductController (ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ProductResponseDTO getProductById(@PathVariable Long productId) {
        return productService.findProductById(productId);
    }

    @GetMapping
    public List <ProductResponseDTO> getAllProducts(){
        return productService.findAllProducts();
    }
    
    @PostMapping
    public ProductResponseDTO createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        return productService.createProduct(productRequestDTO);
    }
    
    @PutMapping("/{productId}")
    public ProductResponseDTO updateProduct (@PathVariable ("productId")Long ProductId, @Valid @RequestBody ProductRequestDTO productRequestDTO){ 
        return productService.updateProduct(ProductId, productRequestDTO);
    }

    @DeleteMapping("/productId")
    public void deleteProduct (@PathVariable("productId") Long productId){
        productService.deleteProduct(productId);
    }
}
