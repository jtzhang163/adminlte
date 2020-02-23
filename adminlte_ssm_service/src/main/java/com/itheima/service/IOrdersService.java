package com.itheima.service;

import com.itheima.domain.Orders;

import java.util.List;

public interface IOrdersService {

    List<Orders> findAll();

    List<Orders> findAll(int page, int pageSize);

    Orders findById(String id);
}
