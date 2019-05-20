package com.erp.controller.technology;

import com.erp.annotation.UpdateMethod;
import com.erp.bean.device.Info;
import com.erp.bean.technology.PageHelper;
import com.erp.bean.technology.Technology;
import com.erp.bean.technology.Technology_requirement;
import com.erp.service.technology.TechnologyService;
import com.erp.service.technology.Technology_requirementService;
import com.erp.utils.PermissionUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author : zjf
 * @Date : 2019/5/18 下午 03:27
 */
@Controller
@RequestMapping("/technologyRequirement")
public class TechnologyRequirementController {
    @Autowired
    Technology_requirementService technology_requirementService;

    @Autowired
    TechnologyService technologyService;

    @RequestMapping("/get_data")
    @ResponseBody
    public List<Technology> getTechnology(){
        List<Technology> technologyList = technologyService.queryAllTechnology();
        return technologyList;
    }

    @RequestMapping("/find")
    public String technologyRequirement_list() {
        return "technologyRequirement_list";
    }

    @RequestMapping("/list")
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

    @RequestMapping("/delete_judge")
    public @ResponseBody String technologyRequirement_delete(){
        return "";
    }

    @RequestMapping("/delete_batch")
    public @ResponseBody Info technologyRequirementdelete(String[] ids){
        int i = technology_requirementService.deleteByids(ids);
        if (i==1){
            return new Info(200,"刪除成功",null);
        }else{
            return new Info(i,"不存在",null);
        }
    }

    @RequestMapping("add_judge")
    public @ResponseBody
    Map<String,String> addDudge(HttpServletRequest request){
        return PermissionUtils.permissionCheck("technologyRequirement:add",request);
    }

    @RequestMapping("/add")
    public String technologyRequirement_add2(){
        return "technologyRequirement_add";
    }

    @RequestMapping("/insert")
    public @ResponseBody Info technologyRequirementadd(Technology_requirement technology_requirement){
        int j = technology_requirementService.judgeById(technology_requirement);
        if(j == 0){
        int i = technology_requirementService.addByTechnologyRequirement(technology_requirement);
        if (i==1){
            return new Info(200,"添加成功",null);
        }else{
            return new Info(i,"添加失敗",null);
        }}else{
            return new Info(j,"添加失敗,工艺要求编号重复",null);
        }
    }

    @RequestMapping("/edit_judge")
    public @ResponseBody String technologyRequirement_edit(){
        return "";
    }

    @RequestMapping("/edit")
    public String technologyRequirement_edit2(){
        return "technologyRequirement_edit";
    }

    @UpdateMethod("technologyRequirement")
    @RequestMapping("/update_all")
    public @ResponseBody Info technologyRequirementnedit(Technology_requirement technology_requirement){
        int i = technology_requirementService.editBytechnologyRequirement(technology_requirement);
        if (i==1){
            return new Info(200,"修改成功",null);
        }else{
            return new Info(i,"修改失敗",null);
        }
    }

    @UpdateMethod("technologyRequirement")
    @RequestMapping("/update_requirement")
    @ResponseBody
    public Info technologyRequirement_update(Technology_requirement technology_requirement){
        int i = technology_requirementService.updateById(technology_requirement);
        if (i==1){
            return new Info(200,"修改成功",null);
        }else{
            return new Info(i,"修改失敗",null);
        }
    }

    @RequestMapping("/search_technologyRequirement_by_technologyRequirementId")
    public @ResponseBody PageHelper technologyRequirement_search(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                                          @RequestParam(name = "rows", required = true, defaultValue = "10") Integer rows,
                                                          String searchValue) throws Exception {
        List<Technology_requirement> technology_requirements = technology_requirementService.searchAllByPage(page, rows,searchValue);
        PageHelper pageHelper = new PageHelper();
        PageInfo pageInfo = new PageInfo(technology_requirements);
        pageHelper.setRows(technology_requirements);
        pageHelper.setTotal((int) pageInfo.getTotal());
        return pageHelper;
    }


    @RequestMapping("/search_technologyRequirement_by_technologyName")
    public @ResponseBody PageHelper technologyRequirement_searchname(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                                              @RequestParam(name = "rows", required = true, defaultValue = "10") Integer rows,
                                                              String searchValue) throws Exception {
        List<Technology_requirement> technology_requirements = technology_requirementService.searchNameAllByPage(page, rows,searchValue);
        PageHelper pageHelper = new PageHelper();
        PageInfo pageInfo = new PageInfo(technology_requirements);
        pageHelper.setRows(technology_requirements);
        pageHelper.setTotal((int) pageInfo.getTotal());
        return pageHelper;
    }
}
