package com.erp.mapper.sys_user;

import com.erp.bean.sys_user.Sys_user;
import com.erp.bean.sys_user.Sys_userExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Sys_userMapper {
    long countByExample(Sys_userExample example);

    int deleteByExample(Sys_userExample example);

    int deleteByPrimaryKey(String id);

    int insert(Sys_user record);

    int insertSelective(Sys_user record);

    List<Sys_user> selectByExample(Sys_userExample example);

    Sys_user selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Sys_user record, @Param("example") Sys_userExample example);

    int updateByExample(@Param("record") Sys_user record, @Param("example") Sys_userExample example);

    int updateByPrimaryKeySelective(Sys_user record);

    int updateByPrimaryKey(Sys_user record);
}