package com.lagou.page.feign;

import com.lagou.pojo.Products;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignFallBack implements ProductFeign{
    @Override
    public Products query(Integer id) {
        return null;
    }

    @Override
    public String findServerPort() {
        return "-1";
    }
}
