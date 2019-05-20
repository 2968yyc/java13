package com.erp.controller.schedule;

import com.erp.annotation.UpdateMethod;
import com.erp.bean.device.Info;
import com.erp.bean.schedule.PageHander;
import com.erp.bean.schedule.Task;
import com.erp.bean.technology.PageHelper;
import com.erp.service.schedule.impl.TaskServiceImpl;
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
    public @ResponseBody
    Map<String,String> addTask1(HttpServletRequest request){
        return PermissionUtils.permissionCheck("task:add",request);
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
        return i!=0?info:new Info(2,"生产派工编号重复",null);
    }


    @RequestMapping("edit_judge")
    public @ResponseBody Map<String,String> editTask1(HttpServletRequest request){
        return PermissionUtils.permissionCheck("task:edit",request);
    }

    @RequestMapping("edit")
    public String editTask2(){
        return "task_edit";
    }

    @UpdateMethod("task")
    @RequestMapping("update_all")
    @ResponseBody
    public Info editTask3(Task task){
        int i=taskService.updateTask(task);
        Info info=new Info(200,"ok",null);
        return i==1?info:null;
    }



    @RequestMapping("delete_judge")
    public @ResponseBody Map<String,String> delTask(HttpServletRequest request){
        return PermissionUtils.permissionCheck("task:delete",request);
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    public Info deleteTask(String[] ids){
        int i =taskService.deleteTaskById(ids);
        Info info=new Info(200,"ok",null);
        return i!=0?info:null;
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
