package com.erp.controller.device;

import com.erp.annotation.UpdateMethod;
import com.erp.bean.QueryVO;
import com.erp.bean.device.Device_check;
import com.erp.bean.device.Info;
import com.erp.service.device.DeviceCheckService;
import com.erp.utils.PermissionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: yyc
 * @Date: 2019/5/18 17:47
 */
@Controller
@RequestMapping("deviceCheck")
public class DeviceCheckController {
    @Autowired
    DeviceCheckService deviceCheckService;

    @RequestMapping("list")
    public @ResponseBody QueryVO<Device_check> list(int page,int rows){
        return deviceCheckService.getDeviceCheckInPage(page, rows);
    }

    @RequestMapping("add_judge")
    public @ResponseBody
    Map<String,String> addDudge(HttpServletRequest request){
        return PermissionUtils.permissionCheck("deviceCheck:add",request);
    }
    @RequestMapping("add")
    public String toAdd(){
        return "deviceCheck_add";
    }
    @RequestMapping(value = "insert")
    public @ResponseBody
    Info insert(Device_check device_check){
        int res = deviceCheckService.addNew(device_check);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else if (res==2){
            return new Info(res,"该设备例检编号已经存在，请更换设备例检编号！",null);
        }else {
            return new Info(res,"添加失败，请稍后再来",null);
        }
    }
    @RequestMapping("edit_judge")
    public @ResponseBody Map<String,String>  editDudge(HttpServletRequest request){
        return PermissionUtils.permissionCheck("deviceCheck:edit",request);
    }
    @RequestMapping("edit")
    public String toEdit(){
        return "deviceCheck_edit";
    }
    @RequestMapping("update")
    public @ResponseBody Info update(Device_check device_check){
        int res = deviceCheckService.update(device_check);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"更新失败！请稍后再来！！",null);
        }
    }

    @RequestMapping("delete_judge")
    public @ResponseBody Map<String,String>  deleteDudge(HttpServletRequest request){
        return PermissionUtils.permissionCheck("deviceCheck:delete",request);
    }

    @RequestMapping("delete_batch")
    public @ResponseBody Info deleteByIDs(String[] ids){
        int res = deviceCheckService.deleteByIDs(ids);
        if (res==1){
            return new Info(200,"删除成功",null);
        }else{
            return new Info(res,"删除失败，请稍后再来！",null);
        }
    }
    @RequestMapping("search_deviceCheck_by_deviceCheckId")
    public @ResponseBody QueryVO<Device_check> searchDeviceCheckByDeviceCheckId(String searchValue,int page,int rows){
        return deviceCheckService.searchDeviceCheckByDeviceCheckId(searchValue, page, rows);

    }
    @RequestMapping("search_deviceCheck_by_deviceName")
    public @ResponseBody QueryVO<Device_check> searchDeviceCheckByDeviceName(String searchValue,int page,int rows){
        return deviceCheckService.searchDeviceCheckByDeviceName(searchValue, page, rows);

    }
    @UpdateMethod("deviceCheck")
    @RequestMapping("update_note")
    public @ResponseBody Info updateNoteById(String deviceCheckId,String deviceCheckResult){
        int res = deviceCheckService.updateNoteById(deviceCheckId,deviceCheckResult);
        if (res==1){
            return new Info(200,"更新成功！",null);
        }else{
            return new Info(res,"更新失败！请稍后再来！",null);
        }
    }




}
