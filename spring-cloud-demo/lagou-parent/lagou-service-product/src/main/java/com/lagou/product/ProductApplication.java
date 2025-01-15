package com.lagou.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.lagou.product.mapper")
public class ProductApplication {
    public static void main(String[] args) {
        System.out.println("测试分支操作123");
        SpringApplication.run(ProductApplication.class,args);
    }
}
