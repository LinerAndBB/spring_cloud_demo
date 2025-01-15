package com.lagou.page.feign;

import com.lagou.pojo.Products;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "lagou-service-product", fallback = ProductFeignFallBack.class)
public interface ProductFeign {

    @GetMapping("/product/query/{id}")
    public Products query(@PathVariable Integer id);

    @RequestMapping("/server/query")
    public String findServerPort();
}
