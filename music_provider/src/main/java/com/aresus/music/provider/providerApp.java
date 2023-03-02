package com.aresus.music.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@MapperScan("com.Aresus.music.dao")
public class providerApp {
    public static void main(String[] args) {
        SpringApplication.run(providerApp.class, args);
    }
}
