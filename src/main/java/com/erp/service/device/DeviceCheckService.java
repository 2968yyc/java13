package com.erp.service.device;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Device_check;

/**
 * @Author: yyc
 * @Date: 2019/5/18 17:50
 */
public interface DeviceCheckService {
    QueryVO<Device_check> getDeviceCheckInPage(int page, int rows);

    int addNew(Device_check device_check);

    int update(Device_check device_check);

    int deleteByIDs(String[] ids);

    QueryVO<Device_check> searchDeviceCheckByDeviceCheckId(String searchValue, int page, int rows);

    QueryVO<Device_check> searchDeviceCheckByDeviceName(String searchValue, int page, int rows);

    int updateNoteById(String deviceCheckId, String deviceCheckResult);

}
