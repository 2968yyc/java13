package com.erp.service;

import com.erp.bean.device.Device;
import com.erp.bean.device.QueryVo;

import java.util.List;

/**
 * @Author: yyc
 * @Date: 2019/5/17 16:13
 */
public interface DeviceService {
    QueryVo getDeviceInPage(int page, int rows);
}
