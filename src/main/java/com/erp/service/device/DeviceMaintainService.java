package com.erp.service.device;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Device_maintain;

/**
 * @Author: yyc
 * @Date: 2019/5/18 20:50
 */
public interface DeviceMaintainService {
    QueryVO getDeviceMaintainInPage(int page, int rows);

    int addNew(Device_maintain device_maintain);

    int update(Device_maintain device_maintain);

    int deleteByIDs(String[] ids);

    QueryVO<Device_maintain> searchDeviceMaintainByDeviceMaintainId(String searchValue, int page, int rows);

    QueryVO<Device_maintain> searchDeviceMaintainByDeviceFaultId(String searchValue, int page, int rows);

    int updateNoteById(String deviceMaintainId, String note);
}
