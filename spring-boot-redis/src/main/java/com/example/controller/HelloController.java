package com.example.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.TimeUnit;

@RestController
public class HelloController {

    private final RedisTemplate<String, String> redisTemplate;

    public HelloController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("hello")
    public String setValue(
            @Valid @NotBlank @RequestParam("key") String key,
            @Valid @NotBlank @RequestParam("value") String value
    ) {
        redisTemplate.opsForValue().set(key, value, 5, TimeUnit.MINUTES);
        return "OK";
    }

    @GetMapping("hello/{key}")
    public String getValue(
            @Valid @NotBlank @PathVariable("key") String key
    ) {
        var value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Key Not Found");
        }
        return value;
    }

    @GetMapping("hello/long-loading-api")
    public String longLoadingApi() {
        var key = "LONG_LOADING_API_KEY";
        var value = redisTemplate.opsForValue().get(key);
        if (value != null) {
            return value;
        }
        //Simulate for calling External Api which is very slow.
        value = callExternalApi();
        redisTemplate.opsForValue().set(key, value, 5, TimeUnit.SECONDS);
        return value;
    }


    private String callExternalApi() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "OK";
    }
}
