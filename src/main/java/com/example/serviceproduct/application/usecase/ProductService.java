package com.example.serviceproduct.application.usecase;

import com.example.serviceproduct.application.ports.input.ProductInputPort;
import com.example.serviceproduct.application.ports.output.ProductRepositoryPort;
import com.example.serviceproduct.domain.errors.ProductServiceException;
import com.example.serviceproduct.domain.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService  implements ProductInputPort {

    private final ProductRepositoryPort productRepositoryPort;
    @Override
    public Mono<Product> save(Product product) {
        return productRepositoryPort.save(product)
                .onErrorMap(e -> {
                    // Manejo específico de excepciones de adaptación si es necesario
                    // Puede lanzar una excepción más orientada a la lógica de negocio
                    return new ProductServiceException("Error al guardar el producto", e);
                })
                .doOnError(e -> log.error("Error en la capa de servicio", e));
    }

    @Override
    public Mono<Product> productFindById(String idProduct) {
        return productRepositoryPort.productFindById(idProduct)
                .onErrorMap( ex -> {
                    // Si ocurre un error en la capa de adaptación, lo envolvemos en una excepción de negocio
                    return new ProductServiceException("Error al obtener el producto con ID: " + idProduct, ex);
                })
                .doOnError(e -> log.error("Error en la capa de servicio al obtener el producto con ID: {}", idProduct, e));
    }
}
