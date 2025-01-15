package com.lagou.product.service;

import com.lagou.pojo.Products;

public interface ProductService {

    //根据商品id查询商品对象
    public Products findById(Integer productId);
}
