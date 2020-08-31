package com.aihaokeji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ProxyProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProxyProviderApplication.class, args);
    }
}
