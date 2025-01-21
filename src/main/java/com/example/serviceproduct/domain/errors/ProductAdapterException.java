package com.example.serviceproduct.domain.errors;

public class ProductAdapterException extends RuntimeException {
    public ProductAdapterException(String message, Throwable cause) {
        super(message, cause);
    }
}
