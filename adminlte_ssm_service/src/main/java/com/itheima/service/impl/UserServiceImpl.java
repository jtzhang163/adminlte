package com.itheima.service.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        //User user = new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), getAuthorities(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(),
                userInfo.getStatus() == 1,true,true,true, getAuthorities(userInfo.getRoles()));
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();

        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            //list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return list;
    }
}
