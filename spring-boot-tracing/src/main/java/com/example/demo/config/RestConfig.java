package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    //    To automatically propagate traces over the network,
//    use the auto-configured RestTemplateBuilder, RestClient.Builder or WebClient.Builder
//    to construct the client.
    @Bean
    public RestClient service1(
            RestClient.Builder restClientBuilder,
            @Value("${service1.api.uri}")
            String service1Uri
    ) {
        return restClientBuilder.baseUrl(service1Uri).build();
    }

    @Bean
    public RestClient service2(
            RestClient.Builder restClientBuilder,
            @Value("${service2.api.uri}")
            String service2Uri
    ) {
        return restClientBuilder.baseUrl(service2Uri).build();
    }
}
