package com.example.serviceproduct.adapters.repository.redis;

import com.example.serviceproduct.adapters.repository.redis.model.ProductRedisEntity;
import reactor.core.publisher.Mono;

public interface ProductRedisRepository {
    Mono<ProductRedisEntity> save(ProductRedisEntity productRedisEntity);
    Mono<ProductRedisEntity>findByKey(String key);

}
