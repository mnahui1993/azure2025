package com.example.serviceproduct.utils;

import com.example.serviceproduct.domain.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static <T> T DeserializeJson(Object object, Class<T> valueType) {
        try {
            // Deserializa el JSON a la clase especificada
            Map<String, Object> map = (Map<String, Object>) object;
            return objectMapper.convertValue(map, valueType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deserializando el Product", e);
        }
    }
}
