package com.erp.controller;

import com.erp.bean.schedule.PageHander;
import com.erp.service.impl.CustomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: xf
 * @Date: 2019/5/17 16:04
 */
@Controller
public class ScheduleController {

    @Autowired
    CustomServiceImpl customService;

    @RequestMapping("custom/find")
    public String findCustom(){
        return "custom_list";
    }

    @RequestMapping("custom/add_judge")
    public String addCustom(){
        return "custom_add";
    }

    @RequestMapping("custom/edit_judge")
    public String editCustom(){
        return "custom_edit";
    }

    @RequestMapping("custom/list")
    @ResponseBody
    public PageHander customPage(int page,int rows){
        return customService.findCustom(page,rows);
    }
}
