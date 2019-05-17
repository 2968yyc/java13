package com.erp.controller.device;

import com.erp.bean.device.Device;
import com.erp.bean.device.QueryVo;
import com.erp.service.DeviceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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






}
