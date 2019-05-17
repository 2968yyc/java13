package com.erp.service;

import com.erp.bean.device.Device;
import com.erp.bean.QueryVO;


/**
 * @Author: yyc
 * @Date: 2019/5/17 16:13
 */
public interface DeviceService {
    QueryVO getDeviceInPage(int page, int rows);

    int addNew(Device device);

    int update(Device device);
}
