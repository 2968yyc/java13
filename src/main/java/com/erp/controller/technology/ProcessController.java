package com.erp.controller.technology;

import com.erp.bean.device.Info;
import com.erp.bean.technology.PageHelper;
import com.erp.bean.technology.Process;
import com.erp.service.technology.ProcessService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author : zjf
 * @Date : 2019/5/19 下午 01:32
 */
@Controller
@RequestMapping("/process")
public class ProcessController {
    @Autowired
    ProcessService processService;

    @RequestMapping("/get_data")
    @ResponseBody
    public List<Process> getProcess(){
        List<Process> processList = processService.queryAllProcess();
        return processList;
    }


    @RequestMapping("/get/{id}")
    @ResponseBody
    public Process queryProcess(@PathVariable("id")String id){
        Process process = processService.queryProcess(id);
        return process;
    }

    @RequestMapping("/find")
    public String process_list(){
        return "process_list";
    }

    @RequestMapping("/list")
    public @ResponseBody
    PageHelper process_list(@RequestParam(name = "page", required = true, defaultValue = "1")
                                    Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "10") Integer
                                    rows) throws Exception {
        List<Process> processes = processService.findAllByPage(page, rows);
        PageHelper pageHelper = new PageHelper();
        PageInfo pageInfo = new PageInfo(processes);
        pageHelper.setRows(processes);
        pageHelper.setTotal((int) pageInfo.getTotal());
        return pageHelper;
    }


    @RequestMapping("/delete_judge")
    public @ResponseBody
    String process_delete(){
        return "";
    }

    @RequestMapping("/delete_batch")
    public @ResponseBody
    Info processdelete(String[] ids){
        int i = processService.deleteByids(ids);
        if (i==1){
            return new Info(200,"刪除成功",null);
        }else{
            return new Info(i,"不存在",null);
        }
    }

    @RequestMapping("/add_judge")
    public @ResponseBody String process_add(){
        return "";
    }

    @RequestMapping("/add")
    public String process_add2(){
        return "process_add";
    }

    @RequestMapping("/insert")
    public @ResponseBody Info processadd(Process process){
        int j = processService.judgeById(process);
        if(j == 0){
        int i = processService.addByProcess(process);
        if (i==1){
            return new Info(200,"添加成功",null);
        }else{
            return new Info(i,"添加失敗",null);
        }}else {
            return new Info(j,"添加失敗，工序编号重复",null);
        }
    }

    @RequestMapping("/edit_judge")
    public @ResponseBody String process_edit(){
        return "";
    }

    @RequestMapping("/edit")
    public String process_edit2(){
        return "process_edit";
    }

    @RequestMapping("/update_all")
    public @ResponseBody Info processedit(Process process){
        int i = processService.editByProcess(process);
        if (i==1){
            return new Info(200,"修改成功",null);
        }else{
            return new Info(i,"修改失敗",null);
        }
    }

    @RequestMapping("/search_process_by_processId")
    public @ResponseBody PageHelper process_search(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                                      @RequestParam(name = "rows", required = true, defaultValue = "10") Integer rows,
                                                      String searchValue) throws Exception {
        List<Process> processes = processService.searchAllByPage(page, rows,searchValue);
        PageHelper pageHelper = new PageHelper();
        PageInfo pageInfo = new PageInfo(processes);
        pageHelper.setRows(processes);
        pageHelper.setTotal((int) pageInfo.getTotal());
        return pageHelper;
    }

    @RequestMapping("/search_process_by_technologyPlanId")
    public @ResponseBody PageHelper process_searchById(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                                   @RequestParam(name = "rows", required = true, defaultValue = "10") Integer rows,
                                                   String searchValue) throws Exception {
        List<Process> processes = processService.searchAllById(page, rows,searchValue);
        PageHelper pageHelper = new PageHelper();
        PageInfo pageInfo = new PageInfo(processes);
        pageHelper.setRows(processes);
        pageHelper.setTotal((int) pageInfo.getTotal());
        return pageHelper;
    }
}
