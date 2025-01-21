package com.example.serviceproduct.adapters.repository.mapper;

import com.example.serviceproduct.adapters.repository.cosmos.model.ProductCosmosEntity;
import com.example.serviceproduct.adapters.repository.redis.model.ProductRedisEntity;
import com.example.serviceproduct.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductRepositoryMapper {
    @Mapping(source = "idProduct",target = "idProduct")
    @Mapping(source = "description",target = "description")
    @Mapping(source = "expirationDate",target = "expirationDate")
    ProductCosmosEntity toProductCosmosEntity(Product product);
    Product toProduct(ProductCosmosEntity productEntity);
    Product toProduct(ProductRedisEntity productRedisEntity);

    ProductRedisEntity toProductRedisEntity(ProductCosmosEntity productCosmosEntity);
}
