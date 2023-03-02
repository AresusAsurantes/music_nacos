package com.Aresus.music.sso;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.Aresus.music.dao")
@EnableDubbo(scanBasePackages = "com.Aresus.music.sso.service")
public class ssoAPP {
    public static void main(String[] args) {
        SpringApplication.run(ssoAPP.class, args);
    }
}
