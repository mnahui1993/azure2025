package com.example.serviceproduct.adapters.repository;

import com.example.serviceproduct.adapters.repository.cosmos.ProductCosmosRepository;
import com.example.serviceproduct.adapters.repository.mapper.ProductRepositoryMapper;
import com.example.serviceproduct.adapters.repository.redis.ProductRedisRepository;
import com.example.serviceproduct.application.ports.output.ProductRepositoryPort;
import com.example.serviceproduct.domain.errors.ProductAdapterException;
import com.example.serviceproduct.domain.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductAdapter implements ProductRepositoryPort {

    private final ProductCosmosRepository productCosmosRepository;
    private final ProductRedisRepository productRedisRepository;
    private final ProductRepositoryMapper productRepositoryMapper;




    @Override
    public Mono<Product> save(Product product) {
        return productCosmosRepository.save(productRepositoryMapper.toProductCosmosEntity(product))
                .map(productRepositoryMapper::toProduct)
                .onErrorMap(e -> new ProductAdapterException("Error al guardar el producto en el repositorio CosmosDB", e)); // Envolvemos la excepción


    }

    @Override
    public Mono<Product> productFindById(String idProduct) {

       return  productRedisRepository.findByKey(idProduct)
                .switchIfEmpty(
                        productCosmosRepository.findByIdProduct(idProduct)
                                .flatMap(productCosmosEntity -> {
                                    var productRedisEntity=productRepositoryMapper.toProductRedisEntity(productCosmosEntity);
                                    return productRedisRepository.save(productRedisEntity)
                                            .doOnSuccess(res -> log.info("Producto con ID: {} guardado en Redis", idProduct))
                                            .doOnError(e -> log.error("Error al guardar producto con ID: {} en Redis", idProduct, e)) // Registro de error en Redis
                                            .thenReturn(productRedisEntity);
                                })
                                .onErrorMap(e -> new ProductAdapterException("Error al obtener el producto desde CosmosDB", e)) // Usar ProductServiceException // Mapea excepciones de CosmosDB
                ).map(productRepositoryMapper::toProduct)
               .doOnError(e -> log.error("Error al obtener el producto con ID: {} desde Redis", idProduct, e)) // Registro de error en Redis
               .onErrorMap(e -> new ProductAdapterException("Error al obtener el producto desde Redis", e)); // Usar ProductServiceException

    }


}
