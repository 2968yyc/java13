package com.erp.service.impl;

import com.erp.bean.device.Device;
import com.erp.bean.device.DeviceExample;
import com.erp.bean.device.QueryVo;
import com.erp.mapper.device.DeviceMapper;
import com.erp.service.DeviceService;
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
    public QueryVo getDeviceInPage(int page, int rows) {

        int l = deviceMapper.countAll();
        PageHelper.startPage(page, rows);

        List<Device> devices = deviceMapper.selectAll();
        return new QueryVo<Device>( l,devices);
    }

    @Override
    public int addNew(Device device) {
        int insert = deviceMapper.insert(device);
        return insert;
    }
}
