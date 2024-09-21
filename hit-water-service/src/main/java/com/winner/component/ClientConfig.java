package com.winner.component;


import org.dom4j.io.SAXReader;
import org.jsoup.Jsoup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SAXReader saxReader() {
        return new SAXReader();
    }

//
//    @Bean
//    public Jsoup jsoup(){
//
//        return new Jsoup();
//
//    }

}
