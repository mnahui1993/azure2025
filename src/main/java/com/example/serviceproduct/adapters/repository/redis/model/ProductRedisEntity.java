package com.example.serviceproduct.adapters.repository.redis.model;


import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRedisEntity {
    private String idProduct;
    private String description;
    private String expirationDate;
}
