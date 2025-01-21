package com.example.serviceproduct.domain.errors;

public class ProductCacheException extends RuntimeException {
    public ProductCacheException(String message, Throwable cause) {
        super(message, cause);
    }
}
