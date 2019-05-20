package com.erp.controller.schedule;

import com.erp.annotation.UpdateMethod;
import com.erp.bean.device.Info;
import com.erp.bean.schedule.PageHander;
import com.erp.bean.schedule.Manufacture;
import com.erp.service.schedule.impl.ManufactureServiceImpl;
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
 * @Author: xf
 * @Date: 2019/5/18 15:06
 */
@Controller
@RequestMapping("manufacture")
public class ManufactureController {



    @Autowired
    ManufactureServiceImpl manufactureService;

    @RequestMapping("get_data")
    @ResponseBody
    public List<Manufacture> getData(){
        return manufactureService.findAllManufacture();
    }

    @RequestMapping("get/{id}")
    @ResponseBody
    public Manufacture queryManufacture(@PathVariable("id") String id){
        return  manufactureService.queryManufacture(id);
    }

    @RequestMapping("find")
    public String findManufacture(){
        return "manufacture_list";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageHander manufacturePage(int page, int rows){
         return manufactureService.findManufacture(page,rows);
    }


    @RequestMapping("add_judge")
    public @ResponseBody
    Map<String,String> addManufacture1(HttpServletRequest request){
        return PermissionUtils.permissionCheck("manufacture:add",request);
    }

    @RequestMapping("add")
    public String addManufacture2(){
        return "manufacture_add";
    }

    @RequestMapping("insert")
    @ResponseBody
    public Info insertManufacture(Manufacture manufacture){
        int i =manufactureService.insertManufacture(manufacture);
        Info info=new Info(200,"ok",null);
        return i!=0?info:new Info(2,"生产批号重复",null);
    }


    @RequestMapping("edit_judge")
    public @ResponseBody Map<String,String> editManufacture1(HttpServletRequest request){
        return PermissionUtils.permissionCheck("manufacture:edit",request);
    }

    @RequestMapping("edit")
    public String editManufacture2(){
        return "manufacture_edit";
    }

    @UpdateMethod("manufacture")
    @RequestMapping("update_all")
    @ResponseBody
    public Info editManufacture3(Manufacture manufacture){
        int i=manufactureService.updateManufacture(manufacture);
        Info info=new Info(200,"ok",null);
        return i==1?info:null;
    }



    @RequestMapping("delete_judge")
    public @ResponseBody Map<String,String> delManufacture(HttpServletRequest request){
        return PermissionUtils.permissionCheck("manufacture:delete",request);
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    public Info deleteManufacture(String[] ids){
        int i =manufactureService.deleteManufactureById(ids);
        Info info=new Info(200,"ok",null);
        return i!=0?info:null;
    }

    @RequestMapping("search_manufacture_by_manufactureId")
    @ResponseBody
    public PageHander searchManufactureById(String searchValue,int page,int rows){
        return manufactureService.searchById(searchValue,page,rows);
    }

    @RequestMapping("search_manufacture_by_manufactureOrderId")
    @ResponseBody
    public PageHander searchManufactureByOrderId(String searchValue,int page,int rows){
        return manufactureService.searchByOrderId(searchValue,page,rows);
    }

    @RequestMapping("search_manufacture_by_manufactureTechnologyName")
    @ResponseBody
    public PageHander searchManufactureByTechnologyName(String searchValue,int page,int rows){
        return manufactureService.searchByTechnologyName(searchValue,page,rows);
    }

 }
