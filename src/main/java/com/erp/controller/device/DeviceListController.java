package com.erp.controller.device;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Device;
import com.erp.bean.device.Info;
import com.erp.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: yyc
 * @Date: 2019/5/17 21:13
 */
@Controller
@RequestMapping("deviceList")
public class DeviceListController {
    @Autowired
    private DeviceService deviceService;


    @RequestMapping("list")
    public @ResponseBody
    QueryVO getDeviceInPage(int page, int rows){
        return deviceService.getDeviceInPage(page, rows);
    }

    @RequestMapping("/add_judge")
    public String addDudge(){
        return "insert";
    }

    @RequestMapping("insert")
    public @ResponseBody Info insert(Device device){
        int res = deviceService.addNew(device);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"该设备号已经存在，请更换设备号！",null);
        }
    }



}
