package com.itheima.dao;

import com.itheima.domain.Product;

import java.util.List;

public interface IProductDao {


    List<Product> findAll();
}
