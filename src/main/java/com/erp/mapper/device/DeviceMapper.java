package com.erp.mapper.device;

import com.erp.bean.device.Device;
import com.erp.bean.device.DeviceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceMapper {
    long countByExample(DeviceExample example);

    int deleteByExample(DeviceExample example);

    int deleteByPrimaryKey(String deviceId);

    int insert(Device record);

    int insertSelective(Device record);

    List<Device> selectByExample(DeviceExample example);

    Device selectByPrimaryKey(String deviceId);

    int updateByExampleSelective(@Param("record") Device record, @Param("example") DeviceExample example);

    int updateByExample(@Param("record") Device record, @Param("example") DeviceExample example);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    int countAll();
    List<Device> selectAll();
    String  getNameById(String deviceId);

    int updateNoteById(@Param("deviceId") String deviceId, @Param("note") String note);
}