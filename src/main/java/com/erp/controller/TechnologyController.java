package com.erp.controller;


import com.erp.bean.technology.PageHelper;
import com.erp.bean.technology.Process;
import com.erp.bean.technology.Technology;
import com.erp.bean.technology.Technology_plan;
import com.erp.bean.technology.Technology_requirement;
import com.erp.service.ProcessService;
import com.erp.service.TechnologyPlanService;
import com.erp.service.TechnologyService;
import com.erp.service.Technology_requirementService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author : zjf
 * @Date : 2019/5/18 下午 01:51
 */
@Controller
public class TechnologyController {
    @Autowired
    Technology_requirementService technology_requirementService;
    @Autowired
    TechnologyPlanService technologyPlanService;
    @Autowired
    TechnologyService technologyService;

    @Autowired
    ProcessService processService;

    @RequestMapping("/technologyRequirement/find")
    public String technologyRequirement_list() {
        return "technologyRequirement_list";
    }

    @RequestMapping("/technologyRequirement/list")
    public @ResponseBody
    PageHelper findAll(@RequestParam(name = "page", required = true, defaultValue = "1")
                               Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "10") Integer
                               rows) throws Exception {
        List<Technology_requirement> Technology_requirements = technology_requirementService.findAllByPage(page, rows);
        PageHelper pageHelper = new PageHelper();
        PageInfo pageInfo = new PageInfo(Technology_requirements);
        pageHelper.setRows(Technology_requirements);
        pageHelper.setTotal((int) pageInfo.getTotal());
        return pageHelper;
    }

    @RequestMapping("/technologyPlan/find")
    public String technologyPlan_list(){
        return "technologyPlan_list";
    }

    @RequestMapping("/technologyPlan/list")
    public @ResponseBody PageHelper findAll2(@RequestParam(name = "page", required = true, defaultValue = "1")
                                                     Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "10") Integer
                                                     rows) throws Exception {
        List<Technology_plan> technology_plans = technologyPlanService.findAllByPage(page, rows);
        PageHelper pageHelper = new PageHelper();
        PageInfo pageInfo = new PageInfo(technology_plans);
        pageHelper.setRows(technology_plans);
        pageHelper.setTotal((int) pageInfo.getTotal());
        return pageHelper;
    }

    @RequestMapping("/technology/find")
    public String technology_list(){
        return "technology_list";
    }

    @RequestMapping("/technology/list")
    public @ResponseBody PageHelper technology_list(@RequestParam(name = "page", required = true, defaultValue = "1")
                                                                Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "10") Integer
                                                                rows) throws Exception {
        List<Technology> technologys = technologyService.findAllByPage(page, rows);
        PageHelper pageHelper = new PageHelper();
        PageInfo pageInfo = new PageInfo(technologys);
        pageHelper.setRows(technologys);
        pageHelper.setTotal((int) pageInfo.getTotal());
        return pageHelper;
    }

    @RequestMapping("/process/find")
    public String process_list(){
        return "process_list";
    }

    @RequestMapping("/process/list")
    public @ResponseBody PageHelper process_list(@RequestParam(name = "page", required = true, defaultValue = "1")
                                                                Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "10") Integer
                                                                rows) throws Exception {
        List<Process> processes = processService.findAllByPage(page, rows);
        PageHelper pageHelper = new PageHelper();
        PageInfo pageInfo = new PageInfo(processes);
        pageHelper.setRows(processes);
        pageHelper.setTotal((int) pageInfo.getTotal());
        return pageHelper;
    }
}