package com.example.serviceproduct.adapters.repository.cosmos.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Container(containerName = "Product")
@Builder
public class ProductCosmosEntity {
    @Id
    @GeneratedValue
    private String id;
    private String idProduct;
    private String description;
    private String expirationDate;
}
