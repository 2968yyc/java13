package com.erp.service;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Device;

/**
 * @Author: yyc
 * @Date: 2019/5/17 16:13
 */
public interface DeviceService {
    QueryVO<Device> getDeviceInPage(int page, int rows);

    int addNew(Device device);
}
