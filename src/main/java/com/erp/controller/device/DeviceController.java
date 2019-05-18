package com.erp.controller.device;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: yyc
 * @Date: 2019/5/17 15:57
 */
@Controller
@RequestMapping("device")
public class DeviceController {


    @RequestMapping("deviceList")
    public String devicelist(){
        return "deviceList";
    }

    @RequestMapping("deviceType")
    public String deviceType(){
        return "deviceType";
    }
    @RequestMapping("deviceCheck")
    public String deviceCheck(){
        return "deviceCheck";
    }
    @RequestMapping("deviceFault")
    public String deviceFault(){
        return "deviceFault";
    }

    @RequestMapping("deviceMaintain")
    public String deviceMaintain(){
        return "deviceMaintain";
    }




}
