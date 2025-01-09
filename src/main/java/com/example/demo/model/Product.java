package com.example.demo.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Data
@Container(containerName = "Product")
@Builder
public class Product {
    @Id
    @GeneratedValue
    private String id;
    private String idProduct;
    private String description;
    private String expirationDate;
}
