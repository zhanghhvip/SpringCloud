package com.aihaokeji;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@MapperScan("com.aihaokeji.mapper")
@SpringBootApplication
public class IndustryCodeApplication {
	public static void main(String[] args) {
		SpringApplication.run(IndustryCodeApplication.class, args);
	}
}
