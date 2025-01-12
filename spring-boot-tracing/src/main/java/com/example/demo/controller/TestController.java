package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class TestController {

    private final RestClient service1;

    private final RestClient service2;

    public TestController(
            @Qualifier("service1")
            RestClient service1,
            @Qualifier("service2")
            RestClient service2
    ) {
        this.service1 = service1;
        this.service2 = service2;
    }

    @GetMapping("/test")
    public String test() {
        return "Hello";
    }

    @GetMapping(value = "/services", produces = MediaType.TEXT_PLAIN_VALUE)
    public String services() {
        String result = service1.get().uri("service").retrieve().body(String.class);
        result += service2.get().uri("service").retrieve().body(String.class);
        return result;
    }

}
