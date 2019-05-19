package com.erp.service.device;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Device_type;

import java.util.List;

/**
 * @Author: yyc
 * @Date: 2019/5/18 10:53
 */
public interface DeviceTypeService {
    QueryVO getDeviceTypeInPage(int page, int rows);

    int addNew(Device_type device_type);

    int update(Device_type device_type);

    int deleteByIDs(String[] ids);

    List<Device_type> getAllData();

    QueryVO<Device_type> searchDeviceTypeByDeviceTypeId(String searchValue, int page, int rows);

    QueryVO<Device_type> searchDeviceTypeByDeviceTypeName(String searchValue, int page, int rows);

    Device_type getByDeviceTypeId(String id);
}
