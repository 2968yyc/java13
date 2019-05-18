package com.erp.service.device;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Device_type;

/**
 * @Author: yyc
 * @Date: 2019/5/18 10:53
 */
public interface DeviceTypeService {
    QueryVO getDeviceTypeInPage(int page, int rows);

    int addNew(Device_type device_type);

    int update(Device_type device_type);

    int deleteByIDs(String[] ids);
}
