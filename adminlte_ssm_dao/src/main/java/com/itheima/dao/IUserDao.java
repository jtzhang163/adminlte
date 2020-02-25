package com.itheima.dao;

import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.Select;

public interface IUserDao  {

    @Select("select * from users where username = #{username}")
    UserInfo findByUsername(String username);
}
