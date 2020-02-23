package com.itheima.dao;

import com.itheima.domain.Member;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IMemberDao {

    @Select("select * from member")
    List<Member> findAll();

    @Select("select * from member where id = #{id}")
    Member findById(String id);
}
