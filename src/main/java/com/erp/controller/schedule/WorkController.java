package com.erp.controller.schedule;

import com.erp.bean.device.Info;
import com.erp.bean.schedule.PageHander;
import com.erp.bean.schedule.Work;
import com.erp.service.schedule.impl.WorkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: xf
 * @Date: 2019/5/18 15:06
 */
@Controller
@RequestMapping("work")
public class WorkController {



    @Autowired
    WorkServiceImpl workService;

    @RequestMapping("get_data")
    public List<Work> getData(){
        return workService.findAllWork();
    }

    @RequestMapping("find")
    public String findWork(){
        return "work_list";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageHander workPage(int page, int rows){
        return workService.findWork(page,rows);
    }


    @RequestMapping("add_judge")
    public String addWork1(){
        return "work_list";
    }

    @RequestMapping("add")
    public String addWork2(){
        return "work_add";
    }

    @RequestMapping("insert")
    @ResponseBody
    public Info insertWork(Work work){
        int i =workService.insertWork(work);
        Info info=new Info(200,"ok",null);
        return i!=0?info:null;
    }


    @RequestMapping("edit_judge")
    public String editWork1(){
        return "work_list";
    }

    @RequestMapping("edit")
    public String editWork2(){
        return "work_edit";
    }
    @RequestMapping("update_all")
    @ResponseBody
    public Info editWork3(Work work){
        int i=workService.updateWork(work);
        Info info=new Info(200,"ok",null);
        return i==1?info:null;
    }



    @RequestMapping("delete_judge")
    public String delWork(){
        return "work_list";
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    public Info deleteWork(String ids){
        int i =workService.deleteWorkById(ids);
        Info info=new Info(200,"ok",null);
        return i==1?info:null;
    }
 }
