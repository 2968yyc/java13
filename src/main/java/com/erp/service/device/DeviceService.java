package com.erp.service.device;

import com.erp.bean.device.Device;
import com.erp.bean.QueryVO;

import java.util.List;


/**
 * @Author: yyc
 * @Date: 2019/5/17 16:13
 */
public interface DeviceService {
    QueryVO getDeviceInPage(int page, int rows);

    int addNew(Device device);

    int update(Device device);

    int deleteByIDs(String[] ids);


    QueryVO<Device> searchDeviceByDeviceId(String searchValue, int page, int rows);

    QueryVO<Device> searchDeviceByDeviceName(String searchValue, int page, int rows);

    QueryVO<Device> searchDeviceByDeviceTypeName(String searchValue, int page, int rows);

    List<Device> getAllData();

    Device getByDeviceId(String id);

    int updateNoteById(String deviceId, String note);
}
