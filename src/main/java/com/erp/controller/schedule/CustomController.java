package com.erp.controller.schedule;

import com.erp.bean.device.Info;
import com.erp.bean.schedule.PageHander;
import com.erp.service.impl.schedule.CustomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: xf
 * @Date: 2019/5/17 16:04
 */
@Controller
@RequestMapping("custom")
public class CustomController {

    @Autowired
    CustomServiceImpl customService;

    @RequestMapping("find")
    public String findCustom(){
        return "custom_list";
    }

    @RequestMapping("add_judge")
    public String addCustom(){
        return "custom_add";
    }

    @RequestMapping("insert")
    public String insertCustom(){


        return "custom_add";
    }

    @RequestMapping("edit_judge")
    public String editCustom(){
        return "custom_edit";
    }

    @RequestMapping("delete_judge")
    public String delCustom(){
        return "custom_list";
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    public Info deleteCustom(String ids){
        int i =customService.deleteCustomById(ids);
        Info info=new Info(200,"ok",null);
        return i==1?info:null;
    }

    @RequestMapping("list")
    @ResponseBody
    public PageHander customPage(int page, int rows){
        return customService.findCustom(page,rows);
    }
}
