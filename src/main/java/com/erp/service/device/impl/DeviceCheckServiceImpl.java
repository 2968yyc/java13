package com.erp.service.device.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Device;
import com.erp.bean.device.DeviceExample;
import com.erp.bean.device.Device_check;
import com.erp.bean.device.Device_checkExample;
import com.erp.mapper.device.DeviceMapper;
import com.erp.mapper.device.Device_checkMapper;
import com.erp.mapper.employee.EmployeeMapper;
import com.erp.service.device.DeviceCheckService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yyc
 * @Date: 2019/5/18 17:50
 */
@Service
public class DeviceCheckServiceImpl implements DeviceCheckService {
    @Autowired
    private Device_checkMapper device_checkMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public QueryVO<Device_check> getDeviceCheckInPage(int page, int rows) {
        Device_checkExample device_checkExample = new Device_checkExample();
        device_checkExample.or();
        long l = device_checkMapper.countByExample(device_checkExample);
        PageHelper.startPage(page, rows);
        List<Device_check> deviceChecks = device_checkMapper.selectByExample(device_checkExample);

        deviceChecks=fillDeviceCheck(deviceChecks);
        return new QueryVO( (int)l,deviceChecks);
    }

    @Override
    public int addNew(Device_check device_check) {
        Device_check device_check1 = device_checkMapper.selectByPrimaryKey(device_check.getDeviceCheckId());
        if (device_check1!=null)
            return 2;
        int insert = device_checkMapper.insert(device_check);
        return insert;
    }

    @Override
    public int update(Device_check device_check) {
        int update = device_checkMapper.updateByPrimaryKeySelective(device_check);
        return update;
    }

    @Override
    public int deleteByIDs(String[] ids) {
        for (String id : ids) {
            int i = device_checkMapper.deleteByPrimaryKey(id);
            if (i!=1)
                return 0;
        }
        return 1;
    }

    @Override
    public QueryVO<Device_check> searchDeviceCheckByDeviceCheckId(String searchValue, int page, int rows) {
        Device_checkExample device_typeExample = new Device_checkExample();
        device_typeExample.or().andDeviceCheckIdLike("%"+searchValue+"%");
        long l = device_checkMapper.countByExample(device_typeExample);
        PageHelper.startPage(page, rows);
        List<Device_check> devices = device_checkMapper.selectByExample(device_typeExample);
        devices=fillDeviceCheck(devices);
        return new QueryVO( (int)l,devices);
    }

    @Override
    public QueryVO<Device_check> searchDeviceCheckByDeviceName(String searchValue, int page, int rows) {
        DeviceExample deviceExample = new DeviceExample();
        deviceExample.or().andDeviceNameLike("%"+searchValue+"%");
        List<Device> devices = deviceMapper.selectByExample(deviceExample);
        if (devices.size()==0){
            return new QueryVO( 0,null);
        }
        List<String> deviceIds = new ArrayList<>();
        for (Device device : devices) {
            String deviceId = device.getDeviceId();
            deviceIds.add(deviceId);
        }

        Device_checkExample device_checkExample = new Device_checkExample();
        device_checkExample.or().andDeviceIdIn(deviceIds);
        long l = device_checkMapper.countByExample(device_checkExample);
        PageHelper.startPage(page,rows);
        List<Device_check> devicelist = device_checkMapper.selectByExample(device_checkExample);

        devicelist=fillDeviceCheck(devicelist);
        return new QueryVO( (int)l,devicelist);
    }

    @Override
    public int updateNoteById(String deviceCheckId, String deviceCheckResult) {
        return device_checkMapper.updateResultById(deviceCheckId,deviceCheckResult);
    }

    private List<Device_check> fillDeviceCheck(List<Device_check> device_checks){
        for (Device_check device_check : device_checks) {
            String deviceId = device_check.getDeviceId();
            String nameById = deviceMapper.getNameById(deviceId);
            device_check.setDeviceName(nameById);
            String EmpName = employeeMapper.selectEmpNameByID(device_check.getDeviceCheckEmpId());
            device_check.setDeviceCheckEmp(EmpName);
        }
        return device_checks;
    }


}
