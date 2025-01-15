package com.lagou.product.service.impl;

import com.lagou.pojo.Products;
import com.lagou.product.mapper.ProductMapper;
import com.lagou.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Products findById(Integer productId) {
        return productMapper.selectById(productId);
    }
}
