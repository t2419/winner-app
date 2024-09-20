package com.winner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class MainServer {
    public static void main(String[] args) throws Exception {

        int i = 5;
        int j = (++i)+(++i)+(++i);
        System.out.println(j);

        SpringApplication.run(MainServer.class, args);
        new CountDownLatch(1).await();
    }

}
