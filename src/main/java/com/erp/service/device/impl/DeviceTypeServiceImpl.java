package com.erp.service.device.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Device_type;
import com.erp.bean.device.Device_typeExample;
import com.erp.mapper.device.Device_typeMapper;
import com.erp.service.device.DeviceTypeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yyc
 * @Date: 2019/5/18 10:53
 */
@Service
public class DeviceTypeServiceImpl implements DeviceTypeService {
    @Autowired
    private Device_typeMapper device_typeMapper;

    @Override
    public QueryVO getDeviceTypeInPage(int page, int rows) {


        Device_typeExample device_typeExample = new Device_typeExample();
        device_typeExample.or();
        long l = device_typeMapper.countByExample(device_typeExample);
        PageHelper.startPage(page, rows);
        List<Device_type> deviceTypes = device_typeMapper.selectByExample(device_typeExample);
        return new QueryVO( (int)l,deviceTypes);
    }

    @Override
    public int addNew(Device_type device_type) {
        int insert = device_typeMapper.insert(device_type);
        return insert;
    }

    @Override
    public int update(Device_type device_type) {
        int update = device_typeMapper.updateByPrimaryKeySelective(device_type);
        return update;
    }

    @Override
    public int deleteByIDs(String[] ids) {
        for (String id : ids) {
            int i = device_typeMapper.deleteByPrimaryKey(id);
            if (i!=1)
                return 0;
        }
        return 1;
    }

    @Override
    public List<Device_type> getAllData() {
        Device_typeExample device_typeExample = new Device_typeExample();
        device_typeExample.or();

        return device_typeMapper.selectByExample(device_typeExample);
    }

    @Override
    public QueryVO<Device_type> searchDeviceTypeByDeviceTypeId(String searchValue, int page, int rows) {
        Device_typeExample device_typeExample = new Device_typeExample();
        device_typeExample.or().andDeviceTypeIdLike("%"+searchValue+"%");
        long l = device_typeMapper.countByExample(device_typeExample);
        PageHelper.startPage(page, rows);
        List<Device_type> devices = device_typeMapper.selectByExample(device_typeExample);

        return new QueryVO( (int)l,devices);

    }

    @Override
    public QueryVO<Device_type> searchDeviceTypeByDeviceTypeName(String searchValue, int page, int rows) {
        Device_typeExample device_typeExample = new Device_typeExample();
        device_typeExample.or().andDeviceTypeNameLike("%"+searchValue+"%");
        long l = device_typeMapper.countByExample(device_typeExample);
        PageHelper.startPage(page, rows);
        List<Device_type> devices = device_typeMapper.selectByExample(device_typeExample);

        return new QueryVO( (int)l,devices);
    }

    @Override
    public Device_type getByDeviceTypeId(String id) {
        Device_type device_type = device_typeMapper.selectByPrimaryKey(id);
        return device_type;
    }
}
