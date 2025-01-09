package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class PruebaDto implements Serializable {
    private String idProduct;
    private String description;
    private String expirationDate;

}
