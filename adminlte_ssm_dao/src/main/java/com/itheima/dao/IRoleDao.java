package com.itheima.dao;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;


import java.util.List;

public interface IRoleDao {

    @Select("select * from role")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = List.class,
                    many = @Many(select = "com.itheima.dao.IPermissionDao.findByRoleId"))
    })
    List<Role> findAll();

    @Select("select * from role where id = #{roleId}")
    Role findById(String roleId);

    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = List.class,
                    many = @Many(select = "com.itheima.dao.IPermissionDao.findByRoleId"))
    })
    List<Role> findByUserId(String userId);

    @Insert("insert into role(rolename, roledesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermissions(String roleId);

    @Insert("insert into role_permission(roleId, permissionId) values(#{roleId}, #{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
