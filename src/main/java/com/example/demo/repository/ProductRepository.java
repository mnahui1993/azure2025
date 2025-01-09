package com.example.demo.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.example.demo.model.Product;

public interface ProductRepository extends ReactiveCosmosRepository<Product, String> {
}
