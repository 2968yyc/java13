package com.erp.service.device;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Device_fault;

import java.util.List;

/**
 * @Author: yyc
 * @Date: 2019/5/18 20:05
 */
public interface DeviceFaultService {
    QueryVO<Device_fault> getDeviceFaultInPage(int page, int rows);

    int addNew(Device_fault device_fault);

    int update(Device_fault device_fault);

    int deleteByIDs(String[] ids);

    QueryVO<Device_fault> searchDeviceFaultByDeviceFaultId(String searchValue, int page, int rows);

    QueryVO<Device_fault> searchDeviceFaultByDeviceName(String searchValue, int page, int rows);

    List<Device_fault> getAllData();

    Device_fault getByDeviceFaultId(String id);

    int updateDetailById(Device_fault device_fault);
}
