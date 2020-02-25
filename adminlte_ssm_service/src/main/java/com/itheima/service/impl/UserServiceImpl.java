package com.itheima.service.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.UserInfo;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = userDao.findByUsername(username);

        //把自己的UserInfo类封装为SpringSecurity要求的UserDetails类
        User user = new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), getAuthorities());
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_USER"));
        //list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return list;
    }
}
