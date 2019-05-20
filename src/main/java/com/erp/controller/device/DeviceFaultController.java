package com.erp.controller.device;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Device_fault;
import com.erp.bean.device.Info;
import com.erp.bean.technology.PageHelper;
import com.erp.service.device.DeviceFaultService;
import com.erp.utils.PermissionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author: yyc
 * @Date: 2019/5/18 20:01
 */
@Controller
@RequestMapping("deviceFault")
public class DeviceFaultController {
    @Autowired
    private DeviceFaultService deviceFaultService;

    @RequestMapping("list")
    public @ResponseBody
    QueryVO getDeviceTypeInPage(int page, int rows){
        return deviceFaultService.getDeviceFaultInPage(page, rows);
    }
    @RequestMapping("add_judge")
    public @ResponseBody
    Map<String,String> addDudge(HttpServletRequest request){
        return PermissionUtils.permissionCheck("deviceFault:add",request);
    }
    @RequestMapping("add")
    public String toAdd(){
        return "deviceFault_add";
    }
    @RequestMapping(value = "insert")
    public @ResponseBody
    Info insert(Device_fault device_fault){
        int res = deviceFaultService.addNew(device_fault);
        if (res==1){
            return new Info(200,"添加成功",null);
        }else if (res==2){
            return new Info(res,"该故障号已经存在，请更换故障编号！",null);
        }else{
            return new Info(res,"添加失败，请稍后再来",null);
        }
    }
    @RequestMapping("edit_judge")
    public @ResponseBody Map<String,String>  editDudge(HttpServletRequest request){
        return PermissionUtils.permissionCheck("deviceFault:edit",request);
    }
    @RequestMapping("edit")
    public String toEdit(){
        return "deviceFault_edit";
    }
    @RequestMapping("update")
    public @ResponseBody Info update(Device_fault device_fault){
        int res = deviceFaultService.update(device_fault);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"添加失败，请稍后再来！",null);
        }
    }
    @RequestMapping("delete_judge")
    public @ResponseBody Map<String,String> deleteDudge(HttpServletRequest request){
        return PermissionUtils.permissionCheck("deviceFault:delete",request);
    }

    @RequestMapping("delete_batch")
    public @ResponseBody Info deleteByIDs(String[] ids){
        int res = deviceFaultService.deleteByIDs(ids);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"添加失败，请稍后再来！",null);
        }
    }
    @RequestMapping("search_deviceFault_by_deviceFaultId")
    public @ResponseBody QueryVO<Device_fault> searchDeviceFaultByDeviceFaultId(String searchValue,int page,int rows){
        return deviceFaultService.searchDeviceFaultByDeviceFaultId(searchValue, page, rows);

    }
    @RequestMapping("search_deviceFault_by_deviceName")
    public @ResponseBody QueryVO<Device_fault> searchDeviceFaultByDeviceFaultName(String searchValue,int page,int rows){
        return deviceFaultService.searchDeviceFaultByDeviceName(searchValue, page, rows);

    }

    @RequestMapping("get_data")
    public @ResponseBody
    List<Device_fault> getData(){
        return deviceFaultService.getAllData();
    }
    @RequestMapping("get/{id}")
    public @ResponseBody Device_fault getByDeviceTypeId(@PathVariable("id") String id){
        return deviceFaultService.getByDeviceFaultId(id);
    }
    @RequestMapping("update_note")
    public @ResponseBody Info updateNote(Device_fault device_fault){

        int res = deviceFaultService.updateDetailById(device_fault);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"添加失败，请稍后再来！",null);
        }
    }


}
