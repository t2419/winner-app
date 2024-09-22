package com.winner.component;


import org.dom4j.io.SAXReader;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ClientConfig {


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.additionalInterceptors(new LoggingInterceptor()).build();
    }

    @Bean
    public SAXReader saxReader() {
        return new SAXReader();
    }


}
