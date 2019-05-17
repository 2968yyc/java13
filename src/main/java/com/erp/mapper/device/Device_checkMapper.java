package com.erp.mapper.device;

import com.erp.bean.device.Device_check;
import com.erp.bean.device.Device_checkExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Device_checkMapper {
    long countByExample(Device_checkExample example);

    int deleteByExample(Device_checkExample example);

    int deleteByPrimaryKey(String deviceCheckId);

    int insert(Device_check record);

    int insertSelective(Device_check record);

    List<Device_check> selectByExample(Device_checkExample example);

    Device_check selectByPrimaryKey(String deviceCheckId);

    int updateByExampleSelective(@Param("record") Device_check record, @Param("example") Device_checkExample example);

    int updateByExample(@Param("record") Device_check record, @Param("example") Device_checkExample example);

    int updateByPrimaryKeySelective(Device_check record);

    int updateByPrimaryKey(Device_check record);
}