package com.erp.controller.technology;

import com.erp.bean.device.Info;
import com.erp.bean.technology.PageHelper;
import com.erp.bean.technology.Technology_plan;
import com.erp.service.technology.TechnologyPlanService;
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
 * @Date : 2019/5/18 下午 03:28
 */
@Controller
@RequestMapping("technologyPlan")
public class TechnologyPlanController {
    @Autowired
    TechnologyPlanService technologyPlanService;

    @RequestMapping("/get/{id}")
    @ResponseBody
    public Technology_plan queryTechnologyPlan(@PathVariable("id")String id){
        Technology_plan technology_plan = technologyPlanService.queryTechnologyPlan(id);
        return technology_plan;
    }

    @RequestMapping("/get_data")
    @ResponseBody
    public List<Technology_plan> getTechnologyPlan(){
        List<Technology_plan> technology_plans = technologyPlanService.queryAllTechnologyPlan();
        return technology_plans;
    }

    @RequestMapping("/find")
    public String technologyPlan_list(){
        return "technologyPlan_list";
    }

    @RequestMapping("/list")
    public @ResponseBody
    PageHelper findAll2(@RequestParam(name = "page", required = true, defaultValue = "1")
                                Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "10") Integer
                                rows) throws Exception {
        List<Technology_plan> technology_plans = technologyPlanService.findAllByPage(page, rows);
        PageHelper pageHelper = new PageHelper();
        PageInfo pageInfo = new PageInfo(technology_plans);
        pageHelper.setRows(technology_plans);
        pageHelper.setTotal((int) pageInfo.getTotal());
        return pageHelper;
    }

    @RequestMapping("/delete_judge")
    public @ResponseBody String technology_delete(){
        return "";
    }

    @RequestMapping("/delete_batch")
    public @ResponseBody
    Info technologyplandelete(String[] ids){
        int i = technologyPlanService.deleteByids(ids);
        if (i==1){
            return new Info(200,"刪除成功",null);
        }else{
            return new Info(i,"不存在",null);
        }
    }

    @RequestMapping("/add_judge")
    public @ResponseBody String technologyPlan_add(){
        return "";
    }

    @RequestMapping("/add")
    public String technologyPlan_add2(){
        return "technologyPlan_add";
    }

    @RequestMapping("/insert")
    public @ResponseBody Info technologyadd(Technology_plan technology_plan){
        int i = technologyPlanService.addByTechnologyPlan(technology_plan);
        if (i==1){
            return new Info(200,"添加成功",null);
        }else{
            return new Info(i,"添加失敗",null);
        }
    }

    @RequestMapping("/edit_judge")
    public @ResponseBody String technologyPlan_edit(){
        return "";
    }

    @RequestMapping("/edit")
    public String technologyPlan_edit2(){
        return "technologyPlan_edit";
    }

    @RequestMapping("/update_all")
    public @ResponseBody Info technologyPlanedit(Technology_plan technology_plan){
        int i = technologyPlanService.editByTechnology(technology_plan);
        if (i==1){
            return new Info(200,"修改成功",null);
        }else{
            return new Info(i,"修改失敗",null);
        }
    }

    @RequestMapping("/search_technologyPlan_by_technologyPlanId")
    public @ResponseBody PageHelper technologyPlan_search(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                                      @RequestParam(name = "rows", required = true, defaultValue = "10") Integer rows,
                                                      String searchValue) throws Exception {
        List<Technology_plan> technology_plans = technologyPlanService.searchAllByPage(page, rows,searchValue);
        PageHelper pageHelper = new PageHelper();
        PageInfo pageInfo = new PageInfo(technology_plans);
        pageHelper.setRows(technology_plans);
        pageHelper.setTotal((int) pageInfo.getTotal());
        return pageHelper;
    }

    @RequestMapping("/search_technologyPlan_by_technologyName")
    public @ResponseBody PageHelper technologyPlan_searchname(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                                          @RequestParam(name = "rows", required = true, defaultValue = "10") Integer rows,
                                                          String searchValue) throws Exception {
        List<Technology_plan> technology_plans = technologyPlanService.searchNameAllByPage(page, rows,searchValue);
        PageHelper pageHelper = new PageHelper();
        PageInfo pageInfo = new PageInfo(technology_plans);
        pageHelper.setRows(technology_plans);
        pageHelper.setTotal((int) pageInfo.getTotal());
        return pageHelper;
    }
}
