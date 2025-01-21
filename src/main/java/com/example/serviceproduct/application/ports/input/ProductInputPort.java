package com.example.serviceproduct.application.ports.input;

import com.example.serviceproduct.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductInputPort {
    Mono<Product> save(Product product);
    Mono<Product> productFindById(String id);
}
