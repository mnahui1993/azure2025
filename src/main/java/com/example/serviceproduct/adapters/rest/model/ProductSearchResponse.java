package com.example.serviceproduct.adapters.rest.model;

import com.azure.core.annotation.Get;
import lombok.*;

@Getter
@Setter
public class ProductSearchResponse {

    private String idProduct;
    private String description;
    private String expirationDate;
}
