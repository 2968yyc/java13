package com.erp.service.device.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Device;
import com.erp.bean.device.DeviceExample;
import com.erp.bean.device.Device_fault;
import com.erp.bean.device.Device_faultExample;
import com.erp.mapper.device.DeviceMapper;
import com.erp.mapper.device.Device_faultMapper;
import com.erp.service.device.DeviceFaultService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yyc
 * @Date: 2019/5/18 20:06
 */
@Service
public class DeviceFaultServiceImpl implements DeviceFaultService {
    @Autowired
    private Device_faultMapper device_faultMapper;
    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public QueryVO<Device_fault> getDeviceFaultInPage(int page, int rows) {
        Device_faultExample device_faultExample = new Device_faultExample();
        device_faultExample.or();
        long l = device_faultMapper.countByExample(device_faultExample);
        PageHelper.startPage(page, rows);
        List<Device_fault> devices = device_faultMapper.selectByExample(device_faultExample);
        devices=fillDeviceCheck(devices);
        return new QueryVO( (int)l,devices);
    }

    @Override
    public int addNew(Device_fault device_fault) {
        int insert = device_faultMapper.insert(device_fault);
        return insert;
    }

    @Override
    public int update(Device_fault device_fault) {
        int update = device_faultMapper.updateByPrimaryKeySelective(device_fault);
        return update;
    }

    @Override
    public int deleteByIDs(String[] ids) {
        for (String id : ids) {
            int i = device_faultMapper.deleteByPrimaryKey(id);
            if (i!=1)
                return 0;
        }
        return 1;
    }

    @Override
    public QueryVO<Device_fault> searchDeviceFaultByDeviceFaultId(String searchValue, int page, int rows) {
        Device_faultExample device_faultExample = new Device_faultExample();
        device_faultExample.or().andDeviceFaultIdLike("%"+searchValue+"%");
        long l = device_faultMapper.countByExample(device_faultExample);
        PageHelper.startPage(page, rows);
        List<Device_fault> devices = device_faultMapper.selectByExample(device_faultExample);
        devices=fillDeviceCheck(devices);
        return new QueryVO( (int)l,devices);
    }

    @Override
    public QueryVO<Device_fault> searchDeviceFaultByDeviceName(String searchValue, int page, int rows) {
        DeviceExample deviceExample = new DeviceExample();
        deviceExample.or().andDeviceNameLike("%"+searchValue+"%");
        List<Device> devices = deviceMapper.selectByExample(deviceExample);
        List<String> deviceIds = new ArrayList<>();
        for (Device device : devices) {
            String deviceId = device.getDeviceId();
            deviceIds.add(deviceId);
        }

        Device_faultExample device_faultExample = new Device_faultExample();
        device_faultExample.or().andDeviceIdIn(deviceIds);
        long l = device_faultMapper.countByExample(device_faultExample);
        PageHelper.startPage(page,rows);
        List<Device_fault> devicelist = device_faultMapper.selectByExample(device_faultExample);

        devicelist=fillDeviceCheck(devicelist);
        return new QueryVO( (int)l,devicelist);
    }

    @Override
    public List<Device_fault> getAllData() {
        Device_faultExample device_faultExample = new Device_faultExample();
        device_faultExample.or();
        return device_faultMapper.selectByExample(device_faultExample);
    }

    @Override
    public Device_fault getByDeviceFaultId(String id) {
        Device_fault device_fault = device_faultMapper.selectByPrimaryKey(id);
        return device_fault;
    }

    private List<Device_fault> fillDeviceCheck(List<Device_fault> devices) {
        for (Device_fault device : devices) {
            String deviceId = device.getDeviceId();
            String name = deviceMapper.getNameById(deviceId);
            device.setDeviceName(name);
        }
        return devices;
    }
}
