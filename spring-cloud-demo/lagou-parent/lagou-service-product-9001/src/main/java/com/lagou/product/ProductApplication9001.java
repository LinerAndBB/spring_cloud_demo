package com.lagou.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.lagou.product.mapper")
public class ProductApplication9001 {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication9001.class,args);
    }
}
