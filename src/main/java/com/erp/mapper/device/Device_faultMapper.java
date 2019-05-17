package com.erp.mapper.device;

import com.erp.bean.device.Device_fault;
import com.erp.bean.device.Device_faultExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Device_faultMapper {
    long countByExample(Device_faultExample example);

    int deleteByExample(Device_faultExample example);

    int deleteByPrimaryKey(String deviceFaultId);

    int insert(Device_fault record);

    int insertSelective(Device_fault record);

    List<Device_fault> selectByExample(Device_faultExample example);

    Device_fault selectByPrimaryKey(String deviceFaultId);

    int updateByExampleSelective(@Param("record") Device_fault record, @Param("example") Device_faultExample example);

    int updateByExample(@Param("record") Device_fault record, @Param("example") Device_faultExample example);

    int updateByPrimaryKeySelective(Device_fault record);

    int updateByPrimaryKey(Device_fault record);
}