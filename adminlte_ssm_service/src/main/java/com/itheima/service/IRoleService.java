package com.itheima.service;

import com.itheima.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll();

    void save(Role role);
}
