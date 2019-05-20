package com.erp.controller.device;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Device_maintain;
import com.erp.bean.device.Info;
import com.erp.service.device.DeviceMaintainService;
import com.erp.service.device.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: yyc
 * @Date: 2019/5/18 20:49
 */
@Controller
@RequestMapping("deviceMaintain")
public class DeviceMaintainController {
    @Autowired
    private DeviceMaintainService deviceMaintainService;

    @RequestMapping("list")
    public @ResponseBody
    QueryVO getDeviceMaintainInPage(int page, int rows){
        return deviceMaintainService.getDeviceMaintainInPage(page, rows);
    }
    @RequestMapping("add_judge")
    public @ResponseBody String  addDudge(){
        //Todo 判断权限
        return null;
    }
    @RequestMapping("add")
    public String toAdd(){
        return "deviceMaintain_add";
    }
    @RequestMapping(value = "insert")
    public @ResponseBody
    Info insert(Device_maintain device_maintain){
        int res = deviceMaintainService.addNew(device_maintain);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"该设备维修编号已经存在，请更换设备维修编号！",null);
        }
    }
    @RequestMapping("edit_judge")
    public @ResponseBody String  editDudge(){
        //Todo 判断权限
        return "";
    }
    @RequestMapping("edit")
    public String toEdit(){
        return "deviceMaintain_edit";
    }
    @RequestMapping("update")
    public @ResponseBody Info update(Device_maintain device_maintain){
        int res = deviceMaintainService.update(device_maintain);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"该设备号已经存在，请更换设备号！",null);
        }
    }
    @RequestMapping("delete_judge")
    public @ResponseBody String  deleteDudge(){
        //Todo 判断权限
        return "";
    }

    @RequestMapping("delete_batch")
    public @ResponseBody Info deleteByIDs(String[] ids){
        int res = deviceMaintainService.deleteByIDs(ids);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"该设备号已经存在，请更换设备号！",null);
        }
    }
    @RequestMapping("search_deviceMaintain_by_deviceMaintainId")
    public @ResponseBody QueryVO<Device_maintain> searchDeviceMaintainByDeviceMaintainId(String searchValue,int page,int rows){
        return deviceMaintainService.searchDeviceMaintainByDeviceMaintainId(searchValue, page, rows);

    }
    @RequestMapping("search_deviceMaintain_by_deviceFaultId")
    public @ResponseBody QueryVO<Device_maintain> searchDeviceMaintainByDeviceFaultId(String searchValue,int page,int rows){
        return deviceMaintainService.searchDeviceMaintainByDeviceFaultId(searchValue, page, rows);

    }
    @RequestMapping("update_note")
    public @ResponseBody Info updateNote(String deviceMaintainId,String note){

        int res = deviceMaintainService.updateNoteById(deviceMaintainId,note);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"该设备号已经存在，请更换设备号！",null);
        }
    }



}
