package com.example.serviceproduct.adapters.config;

import com.example.serviceproduct.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(StringRedisTemplate stringRedisTemplate) {


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDefaultTyping(null);  // Elimina el campo @class

        // Serializador para los valores (objetos almacenados en Redis)
        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);

        // Serializador para las claves (normalmente String)
        StringRedisSerializer keySerializer = new StringRedisSerializer();

        // Configuración del contexto de serialización
        RedisSerializationContext<Object, Object> serializationContext = RedisSerializationContext
                .newSerializationContext(keySerializer)  // Configuración para las claves
                .value(valueSerializer)  // Configuración para los valores
                .build();





        // Se crea y se devuelve el ReactiveRedisTemplate
        return new ReactiveRedisTemplate((ReactiveRedisConnectionFactory) stringRedisTemplate.getConnectionFactory(),serializationContext);
    }


/*
    @Bean
    public RedisSerializer<Object> redisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.setDefaultTyping(null);  // Evita la inclusión de @class

        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }

    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new ReactiveRedisTemplate<>((ReactiveRedisConnectionFactory) redisConnectionFactory, redisSerializer());
    }*/
/*
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // Configuración del ObjectMapper para evitar la inclusión de @class
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDefaultTyping(null);  // Elimina el campo @class

        // Serializador para los valores (objetos almacenados en Redis)
        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);

        // Serializador para las claves (normalmente String)
        StringRedisSerializer keySerializer = new StringRedisSerializer();

        // Configuración del contexto de serialización
        RedisSerializationContext<Object, Object> serializationContext = RedisSerializationContext
                .newSerializationContext(keySerializer)  // Configuración para las claves
                .value(valueSerializer)  // Configuración para los valores
                .build();

        return new ReactiveRedisTemplate((ReactiveRedisConnectionFactory) redisConnectionFactory,serializationContext);
    }*/



}
