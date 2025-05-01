package com.cristhiang.ecommerce_microservices.productservice.productModel;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="product")
public class Product {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)

    @Column(name = "id")
    private long productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "productModel")
    private String productModel;

    @Column(name = "productPrice")
    private float productPrice;

    @Column(name = "productDescription")
    private String productDescription;

}
