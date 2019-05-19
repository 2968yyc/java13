package com.erp.controller.schedule;

import com.erp.bean.device.Info;
import com.erp.bean.schedule.PageHander;
import com.erp.bean.schedule.Task;
import com.erp.service.schedule.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: xf
 * @Date: 2019/5/18 15:06
 */
@Controller
@RequestMapping("task")
public class TaskController {



    @Autowired
    TaskServiceImpl taskService;

    @RequestMapping("get_data")
    @ResponseBody
    public List<Task> getData(){
        return taskService.findAllTask();
    }

    @RequestMapping("get/{id}")
    @ResponseBody
    public Task queryTask(@PathVariable("id") String id){
        return  taskService.queryTask(id);
    }

    @RequestMapping("find")
    public String findTask(){
        return "task_list";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageHander taskPage(int page, int rows){
         return taskService.findTask(page,rows);
    }


    @RequestMapping("add_judge")
    public String addTask1(){
        return "task_list";
    }

    @RequestMapping("add")
    public String addTask2(){
        return "task_add";
    }

    @RequestMapping("insert")
    @ResponseBody
    public Info insertTask(Task task){
        int i =taskService.insertTask(task);
        Info info=new Info(200,"ok",null);
        return i!=0?info:null;
    }


    @RequestMapping("edit_judge")
    public String editTask1(){
        return "task_list";
    }

    @RequestMapping("edit")
    public String editTask2(){
        return "task_edit";
    }
    @RequestMapping("update_all")
    @ResponseBody
    public Info editTask3(Task task){
        int i=taskService.updateTask(task);
        Info info=new Info(200,"ok",null);
        return i==1?info:null;
    }



    @RequestMapping("delete_judge")
    public String delTask(){
        return "task_list";
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    public Info deleteTask(String ids){
        int i =taskService.deleteTaskById(ids);
        Info info=new Info(200,"ok",null);
        return i==1?info:null;
    }

    @RequestMapping("search_task_by_taskId")
    @ResponseBody
    public PageHander searchTaskById(String searchValue,int page,int rows){
        return taskService.searchById(searchValue,page,rows);
    }

    @RequestMapping("search_task_by_taskWorkId")
    @ResponseBody
    public PageHander searchTaskByWorkId(String searchValue,int page,int rows){
        return taskService.searchByWorkId(searchValue,page,rows);
    }

    @RequestMapping("search_task_by_taskManufactureSn")
    @ResponseBody
    public PageHander searchTaskByManufactureSn(String searchValue,int page,int rows){
        return taskService.searchByManufactureSn(searchValue,page,rows);
    }
 }
