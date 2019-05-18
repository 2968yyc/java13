package com.erp.controller.schedule;

import com.erp.bean.device.Info;
import com.erp.bean.schedule.Custom;
import com.erp.bean.schedule.PageHander;
import com.erp.service.schedule.impl.CustomServiceImpl;
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

    @RequestMapping("list")
    @ResponseBody
    public PageHander customPage(int page, int rows){
        return customService.findCustom(page,rows);
    }


    @RequestMapping("add_judge")
    public String addCustom1(){
        return "custom_list";
    }

    @RequestMapping("add")
    public String addCustom2(){
        return "custom_add";
    }

    @RequestMapping("insert")
    @ResponseBody
    public Info insertCustom(Custom custom){
        int i =customService.insertCustom(custom);
        Info info=new Info(200,"ok",null);
        return i!=0?info:null;
    }


    @RequestMapping("edit_judge")
    public String editCustom1(){
        return "custom_list";
    }

    @RequestMapping("edit")
    public String editCustom2(){
        return "custom_edit";
    }
    @RequestMapping("update_all")
    @ResponseBody
    public Info editCustom3(Custom custom){
        int i=customService.updateCustom(custom);
        Info info=new Info(200,"ok",null);
        return i==1?info:null;
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


}
