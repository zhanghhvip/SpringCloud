package com.aihaokeji;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
@EnableScheduling
@MapperScan("com.aihaokeji.mapper")
@SpringBootApplication
public class RealTimeBidApplication {
    public static void main(String[] args) {
        SpringApplication.run(RealTimeBidApplication.class,args);
    }
    @Bean
    public RestTemplate restTemplate(){
        // 添加内容转换器,使用默认的内容转换器
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate ;
    }
}
