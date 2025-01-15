package com.lagou.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.pojo.Products;
import org.apache.ibatis.annotations.Mapper;

// mybatis增强版，实现该接口即可实现对应的基本方法
@Mapper
public interface ProductMapper extends BaseMapper<Products> {

  public void mytest2();
}
