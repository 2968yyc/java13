package com.erp.service.device.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Device;
import com.erp.bean.device.DeviceExample;
import com.erp.bean.device.Device_type;
import com.erp.bean.device.Device_typeExample;
import com.erp.mapper.device.DeviceMapper;
import com.erp.mapper.device.Device_typeMapper;
import com.erp.service.device.DeviceService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yyc
 * @Date: 2019/5/17 16:14
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public QueryVO getDeviceInPage(int page, int rows) {

        int l = deviceMapper.countAll();
        PageHelper.startPage(page, rows);
        DeviceExample deviceExample = new DeviceExample();
        deviceExample.or();
        List<Device> devices = deviceMapper.selectByExample(deviceExample);
        return new QueryVO( l,devices);
    }

    @Override
    public int addNew(Device device) {
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


}
