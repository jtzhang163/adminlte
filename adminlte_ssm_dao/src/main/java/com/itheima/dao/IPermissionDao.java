package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission")
    List<Permission> findAll();

    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findByRoleId(String roleId);

    @Insert("insert into permission(permissionName, url) values (#{permissionName}, #{url})")
    void save(Permission permission);
}
