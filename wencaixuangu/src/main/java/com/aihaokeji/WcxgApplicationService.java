package com.aihaokeji;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@MapperScan("com.aihaokeji.mapper")
@EnableScheduling
@SpringBootApplication
public class WcxgApplicationService {
    public static void main(String[] args) {
        SpringApplication.run(WcxgApplicationService.class,args);
    }
}
