package com.example.serviceproduct.adapters.repository.cosmos;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.example.serviceproduct.adapters.repository.cosmos.model.ProductCosmosEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductCosmosRepository extends ReactiveCosmosRepository<ProductCosmosEntity, String> {
    Mono<ProductCosmosEntity> findByIdProduct(String idProduct);
}
