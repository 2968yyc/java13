package com.erp.mapper.device;

import com.erp.bean.device.Device_maintain;
import com.erp.bean.device.Device_maintainExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Device_maintainMapper {
    long countByExample(Device_maintainExample example);

    int deleteByExample(Device_maintainExample example);

    int deleteByPrimaryKey(String deviceMaintainId);

    int insert(Device_maintain record);

    int insertSelective(Device_maintain record);

    List<Device_maintain> selectByExample(Device_maintainExample example);

    Device_maintain selectByPrimaryKey(String deviceMaintainId);

    int updateByExampleSelective(@Param("record") Device_maintain record, @Param("example") Device_maintainExample example);

    int updateByExample(@Param("record") Device_maintain record, @Param("example") Device_maintainExample example);

    int updateByPrimaryKeySelective(Device_maintain record);

    int updateByPrimaryKey(Device_maintain record);
}