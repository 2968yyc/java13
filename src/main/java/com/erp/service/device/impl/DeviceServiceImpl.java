package com.erp.service.device.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Device;
import com.erp.bean.device.DeviceExample;
import com.erp.bean.device.Device_type;
import com.erp.bean.device.Device_typeExample;
import com.erp.mapper.device.DeviceMapper;
import com.erp.mapper.device.Device_typeMapper;
import com.erp.mapper.employee.EmployeeMapper;
import com.erp.service.device.DeviceService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yyc
 * @Date: 2019/5/17 16:14
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private Device_typeMapper device_typeMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public QueryVO getDeviceInPage(int page, int rows) {

        int l = deviceMapper.countAll();
        PageHelper.startPage(page, rows);
        DeviceExample deviceExample = new DeviceExample();
        deviceExample.or();
        List<Device> devices = deviceMapper.selectByExample(deviceExample);
        devices = fillDevices(devices);
        return new QueryVO( l,devices);
    }

    @Override
    public int addNew(Device device) {
        Device device1 = deviceMapper.selectByPrimaryKey(device.getDeviceId());
        if (device1!=null){
            return 2;
        }
        int insert = deviceMapper.insert(device);
        return insert;
    }

    @Override
    public int update(Device device) {
        int update = deviceMapper.updateByPrimaryKeySelective(device);
        return update;
    }

    @Override
    public int deleteByIDs(String[] ids) {
        for (String id : ids) {
            int i = deviceMapper.deleteByPrimaryKey(id);
            if (i!=1)
                return 0;
        }
        return 1;
    }

    @Override
    public QueryVO<Device> searchDeviceByDeviceId(String searchValue, int page, int rows) {
        DeviceExample deviceExample = new DeviceExample();
        deviceExample.or().andDeviceIdLike("%"+searchValue+"%");
        long l = deviceMapper.countByExample(deviceExample);
        PageHelper.startPage(page, rows);
        List<Device> devices = deviceMapper.selectByExample(deviceExample);
        devices = fillDevices(devices);
        return new QueryVO( (int)l,devices);
    }

    @Override
    public QueryVO<Device> searchDeviceByDeviceName(String searchValue, int page, int rows) {
        DeviceExample deviceExample = new DeviceExample();
        deviceExample.or().andDeviceNameLike("%"+searchValue+"%");
        long l = deviceMapper.countByExample(deviceExample);
        PageHelper.startPage(page, rows);
        List<Device> devices = deviceMapper.selectByExample(deviceExample);
        devices = fillDevices(devices);
        return new QueryVO( (int)l,devices);
    }

    @Override
    public QueryVO<Device> searchDeviceByDeviceTypeName(String searchValue, int page, int rows) {
        //先查到对应设备名id
        Device_typeExample device_typeExample = new Device_typeExample();
        device_typeExample.or().andDeviceTypeNameLike("%"+searchValue+"%");
        List<Device_type> deviceTypes = device_typeMapper.selectByExample(device_typeExample);
        if (deviceTypes.size()==0){
            return new QueryVO<>(0,new ArrayList<Device>());
        }
        List<String> ids = new ArrayList<>();
        for (Device_type deviceType : deviceTypes) {
            ids.add(deviceType.getDeviceTypeId());
        }
        DeviceExample deviceExample = new DeviceExample();
        deviceExample.or().andDeviceTypeIdIn(ids);
        long l = deviceMapper.countByExample(deviceExample);
        PageHelper.startPage(page, rows);
        List<Device> devices = deviceMapper.selectByExample(deviceExample);
        devices = fillDevices(devices);
        return new QueryVO( (int)l,devices);
    }

    @Override
    public List<Device> getAllData() {
        DeviceExample deviceExample = new DeviceExample();
        deviceExample.or();
        List<Device> devices = deviceMapper.selectByExample(deviceExample);
        return devices;
    }

    @Override
    public Device getByDeviceId(String id) {
        return deviceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateNoteById(String deviceId, String note) {
        return deviceMapper.updateNoteById(deviceId,note);
    }

    public List<Device> fillDevices(List<Device> devices){
        for (Device device : devices) {
            String deviceTypeId = device.getDeviceTypeId();
            Device_type device_type = device_typeMapper.selectByPrimaryKey(deviceTypeId);
            String EmpName = employeeMapper.selectEmpNameByID(device.getDeviceKeeperId());
            device.setDeviceKeeper(EmpName);
            device.setDeviceTypeName(device_type.getDeviceTypeName());
        }
        return devices;
    }


}
