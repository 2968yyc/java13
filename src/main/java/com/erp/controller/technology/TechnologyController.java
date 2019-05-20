package com.erp.controller.technology;


import com.erp.annotation.UpdateMethod;
import com.erp.bean.device.Info;
import com.erp.bean.technology.PageHelper;
import com.erp.bean.technology.Process;
import com.erp.bean.technology.Technology;
import com.erp.service.technology.ProcessService;
import com.erp.service.technology.TechnologyService;
import com.erp.utils.PermissionUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author : zjf
 * @Date : 2019/5/18 下午 01:51
 */
@Controller
@RequestMapping("/technology")
public class TechnologyController {
    @Autowired
    TechnologyService technologyService;

    @RequestMapping("/get/{id}")
    @ResponseBody
    public Technology queryTechnology(@PathVariable("id")String id){
        Technology technology = technologyService.queryTechnology(id);
        return technology;
    }

    @RequestMapping("/find")
    public String technology_list(){
        return "technology_list";
    }

    @RequestMapping("/list")
    public @ResponseBody PageHelper technology_list(@RequestParam(name = "page", required = true, defaultValue = "1")
                                                                Integer page, @RequestParam(name = "rows", required = true, defaultValue = "10") Integer
                                                                rows) throws Exception {
        List<Technology> technologys = technologyService.findAllByPage(page, rows);
        PageHelper pageHelper = new PageHelper();
        PageInfo pageInfo = new PageInfo(technologys);
        pageHelper.setRows(technologys);
        pageHelper.setTotal((int) pageInfo.getTotal());
        return pageHelper;
    }

    @RequestMapping("/delete_judge")
    public @ResponseBody String technology_delete(){
        return "";
    }

    @RequestMapping("/delete_batch")
    public @ResponseBody Info technologydelete(String[] ids){
        int i = technologyService.deleteByids(ids);
        if (i==1){
            return new Info(200,"刪除成功",null);
        }else{
            return new Info(i,"不存在",null);
        }
    }

    @RequestMapping("add_judge")
    public @ResponseBody
    Map<String,String> addDudge(HttpServletRequest request){
        return PermissionUtils.permissionCheck("technology:add",request);
    }

    @RequestMapping("/add")
    public String technology_add2(){
        return "technology_add";
    }

    @RequestMapping("/insert")
    public @ResponseBody Info technologyadd(Technology technology){
        int j = technologyService.judgeById(technology);
        if(j == 0){
        int i = technologyService.addByTechnology(technology);
        if (i==1){
            return new Info(200,"添加成功",null);
        }else{
            return new Info(i,"添加失敗",null);
        }}else {
            return new Info(j,"添加失敗,工艺编号重复",null);
        }
    }

    @RequestMapping("/edit_judge")
    public @ResponseBody String technology_edit(){
        return "";
    }

    @RequestMapping("/edit")
    public String technology_edit2(){
        return "technology_edit";
    }

    @UpdateMethod("technology")
    @RequestMapping("/update_all")
    public @ResponseBody Info technologyedit(Technology technology){
        int i = technologyService.editByTechnology(technology);
        if (i==1){
            return new Info(200,"修改成功",null);
        }else{
            return new Info(i,"修改失敗",null);
        }
    }

    @RequestMapping("/search_technology_by_technologyId")
    public @ResponseBody PageHelper technology_search(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                                      @RequestParam(name = "rows", required = true, defaultValue = "10") Integer rows,
                                                      String searchValue) throws Exception {
        List<Technology> technologys = technologyService.searchAllByPage(page, rows,searchValue);
        PageHelper pageHelper = new PageHelper();
        PageInfo pageInfo = new PageInfo(technologys);
        pageHelper.setRows(technologys);
        pageHelper.setTotal((int) pageInfo.getTotal());
        return pageHelper;
    }

    @RequestMapping("/search_technology_by_technologyName")
    public @ResponseBody PageHelper technology_searchname(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                                      @RequestParam(name = "rows", required = true, defaultValue = "10") Integer rows,
                                                      String searchValue) throws Exception {
        List<Technology> technologys = technologyService.searchNameAllByPage(page, rows,searchValue);
        PageHelper pageHelper = new PageHelper();
        PageInfo pageInfo = new PageInfo(technologys);
        pageHelper.setRows(technologys);
        pageHelper.setTotal((int) pageInfo.getTotal());
        return pageHelper;
    }

    @RequestMapping("/get_data")
    @ResponseBody
    public List<Technology> getTechnology(){
        List<Technology> technologyList = technologyService.queryAllTechnology();
        return technologyList;
    }
}