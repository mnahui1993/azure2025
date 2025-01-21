package com.example.serviceproduct.adapters.rest.mapper;

import com.example.serviceproduct.adapters.rest.model.ProductSearchRequest;
import com.example.serviceproduct.adapters.rest.model.ProductSearchResponse;
import com.example.serviceproduct.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductRestMapper {

    @Mapping(source = "idProduct",target = "idProduct")
    @Mapping(source = "description",target = "description")
    @Mapping(source = "expirationDate",target = "expirationDate")
    ProductSearchResponse toProductSearchResponse(Product product);
    Product toProduct(ProductSearchRequest productSearchRequest);
}
