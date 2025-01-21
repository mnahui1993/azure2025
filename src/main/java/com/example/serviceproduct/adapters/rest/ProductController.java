package com.example.serviceproduct.adapters.rest;

import com.example.serviceproduct.adapters.rest.mapper.ProductRestMapper;
import com.example.serviceproduct.adapters.rest.model.ProductSearchRequest;
import com.example.serviceproduct.adapters.rest.model.ProductSearchResponse;
import com.example.serviceproduct.application.ports.input.ProductInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductInputPort productInputPort;
    private final ProductRestMapper productRestMapper;

    @GetMapping("/product/{id}")
    public Mono<ProductSearchResponse> getProduct(@PathVariable(name = "id") String id){
        return productInputPort.productFindById(id).map(productRestMapper::toProductSearchResponse);
    }

    @PostMapping("/product")
    public Mono<ProductSearchResponse> saveProduct(@Valid @RequestBody  ProductSearchRequest productSearchRequest){
        return productInputPort.save(productRestMapper.toProduct(productSearchRequest))
                .map(productRestMapper::toProductSearchResponse);
    }
}
