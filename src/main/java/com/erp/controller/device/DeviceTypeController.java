package com.erp.controller.device;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Device;
import com.erp.bean.device.Device_type;
import com.erp.bean.device.Info;
import com.erp.service.device.DeviceService;
import com.erp.service.device.DeviceTypeService;
import com.erp.utils.PermissionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * @Author: yyc
 * @Date: 2019/5/18 10:13
 */
@Controller
@RequestMapping("deviceType")
public class DeviceTypeController {

    @Autowired
    private DeviceTypeService deviceTypeService;

    @RequestMapping("list")
    public @ResponseBody QueryVO getDeviceTypeInPage(int page, int rows){
        return deviceTypeService.getDeviceTypeInPage(page, rows);
    }

    @RequestMapping("add_judge")
    public @ResponseBody
    Map<String,String> addDudge(HttpServletRequest request){
        return PermissionUtils.permissionCheck("deviceType:add",request);
    }
    @RequestMapping("add")
    public String toAdd(){
        return "deviceType_add";
    }
    @RequestMapping(value = "insert")
    public @ResponseBody Info insert(Device_type device_type){
        int res = deviceTypeService.addNew(device_type);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"该设备种类编号已经存在，请更换设备种类编号！",null);
        }
    }

    @RequestMapping("edit_judge")
    public @ResponseBody Map<String,String>  editDudge(HttpServletRequest request){
        return PermissionUtils.permissionCheck("deviceType:edit",request);
    }
    @RequestMapping("edit")
    public String toEdit(){
        return "deviceType_edit";
    }
    @RequestMapping("update")
    public @ResponseBody Info update(Device_type device_type){
        int res = deviceTypeService.update(device_type);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"该设备号已经存在，请更换设备号！",null);
        }
    }

    @RequestMapping("delete_judge")
    public @ResponseBody Map<String,String> deleteDudge(HttpServletRequest request){
        return PermissionUtils.permissionCheck("deviceType:delete",request);
    }

    @RequestMapping("delete_batch")
    public @ResponseBody Info deleteByIDs(String[] ids){
        int res = deviceTypeService.deleteByIDs(ids);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"该设备号已经存在，请更换设备号！",null);
        }
    }
    @RequestMapping("search_deviceType_by_deviceTypeId")
    public @ResponseBody QueryVO<Device_type> searchDeviceTypeByDeviceTypeId(String searchValue,int page,int rows){
        return deviceTypeService.searchDeviceTypeByDeviceTypeId(searchValue, page, rows);

    }
    @RequestMapping("search_deviceType_by_deviceTypeName")
    public @ResponseBody QueryVO<Device_type> searchDeviceTypeBydeviceTypeName(String searchValue,int page,int rows){
        return deviceTypeService.searchDeviceTypeByDeviceTypeName(searchValue, page, rows);

    }

    @RequestMapping("update_all")
    public @ResponseBody Info updateDeviceTypeById(Device_type device_type){
        int res = deviceTypeService.update(device_type);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"该设备号已经存在，请更换设备号！",null);
        }
    }


    @RequestMapping("get_data")
    public @ResponseBody List<Device_type> getData(){
        return deviceTypeService.getAllData();
    }

    @RequestMapping("get/{id}")
    public @ResponseBody Device_type getByDeviceTypeId(@PathVariable("id") String id){
        return deviceTypeService.getByDeviceTypeId(id);
    }
}
