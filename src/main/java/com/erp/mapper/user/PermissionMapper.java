package com.erp.mapper.user;

import com.erp.bean.user.Permission;
import com.erp.bean.user.PermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    long countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    String getPermissionIdByRoleId(String roleId);

    int updatePermissionByRoleId(@Param("permission") String permission, @Param("id") String roleId);
}