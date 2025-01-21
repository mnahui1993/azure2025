package com.example.serviceproduct.adapters.repository.redis;

import com.example.serviceproduct.adapters.repository.redis.model.ProductRedisEntity;
import com.example.serviceproduct.domain.errors.ProductCacheException;
import com.example.serviceproduct.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductRedisRepositoryImpl implements ProductRedisRepository {

    private final ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;

    @Override
    public Mono<ProductRedisEntity> save(ProductRedisEntity productRedisEntity) {
        return reactiveRedisTemplate.opsForValue()
                .set(productRedisEntity.getIdProduct(), productRedisEntity, Duration.ofMinutes(1))
                .thenReturn(productRedisEntity)
                .doOnSuccess(res -> log.info("Producto con ID: {} guardado correctamente en Redis", productRedisEntity.getIdProduct()))
                .doOnError(e -> log.error("Error al guardar el producto con ID: {} en Redis", productRedisEntity.getIdProduct(), e))
                .onErrorMap(e -> new ProductCacheException("Error al guardar el producto en Redis", e))
                .doOnTerminate(() -> log.info("Operación de guardado terminada para el producto con ID: {}", productRedisEntity.getIdProduct()));
    }

    @Override
    public Mono<ProductRedisEntity> findByKey(String key) {
        return  reactiveRedisTemplate.opsForValue().get(key)
                .map(object-> JsonUtils.DeserializeJson(object, ProductRedisEntity.class))
                .switchIfEmpty(Mono.just(new ProductRedisEntity()))
                .doOnSuccess(res -> log.info("Producto con ID: {} encontrado en Redis", key))
                .doOnError(e -> log.error("Error al obtener el producto con ID: {} desde Redis", key, e))
                .onErrorMap(e -> new ProductCacheException("Error al obtener el producto desde Redis", e))
                .doOnTerminate(() -> log.info("Operación de búsqueda terminada para el producto con ID: {}", key));
    }


}
