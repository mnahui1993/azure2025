package com.example.demo;


import com.example.demo.dto.ProductDto;
import com.example.demo.dto.PruebaDto;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;


@RestController
@RequestMapping("/api/v1")
public class Controller {


    @Value("${database.secret.value}")
    private String mySecret;

     @Autowired
    private StringRedisTemplate template;;

     @Autowired
     private ProductRepository productRepository;





    @GetMapping("/getsecret")
    public Model secretoo() {
        return Model.builder().secret(mySecret).build();
    }

    @GetMapping("/getproduct")
    public Flux<Product> getproduct()   {

        return productRepository.findAll();

    }
    @PostMapping("/product")
    public Mono<Product> save(@RequestBody ProductDto productDto){
       var product= Product.builder()
                .idProduct(productDto.getIdProduct())
                .description(productDto.getDescription())
                .expirationDate(productDto.getExpirationDate())
                .build();
        return productRepository.save(product);
    }

    @GetMapping("/redis/product/{key}")
    public void insertarConExpiracion(@PathVariable(name = "key") String key) {
       ValueOperations<String, String> ops = this.template.opsForValue();
        //String key = "mm";
        if(!this.template.hasKey(key)){
            ops.set(key, "Hello World" + key);
        }
    }
}


