package com.example.serviceproduct.adapters.rest.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchRequest {

    @NotBlank(message = "id product vacio")
    @NotEmpty
    private String idProduct;
    @NotBlank(message = "description vacio")
    private String description;
    @NotBlank(message = "expirationDate vacio")
    private String expirationDate;
}
