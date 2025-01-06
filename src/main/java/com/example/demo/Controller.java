package com.example.demo;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    @Value("${database.secret.value}")
    private String mySecret;

    @GetMapping("/api/v1/getsecret")
    public Model secretoo() throws JsonProcessingException {

        Model model= new Model();
        model.setSecret(mySecret);
        return model;


    }
}
