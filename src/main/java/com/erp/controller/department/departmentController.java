package com.erp.controller.department;

import com.erp.bean.QueryVO;
import com.erp.bean.department.Department;
import com.erp.bean.device.Info;
import com.erp.service.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: xu
 * @Date: 2019/5/18 9:49
 */
@Controller
@RequestMapping("department")
public class departmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("find")
    public String findDepartment(){
        return "department_list";
    }

    @RequestMapping("list")
    public @ResponseBody
    QueryVO findDepartmentInPage(int page, int rows){
        return departmentService.getDepartmentInPage(page,rows);
    }

    @RequestMapping("get_data")
    public @ResponseBody
    List<Department> departmentGetData(){
        return departmentService.getDepartmentData();
    }

    @RequestMapping("get/{id}")
    @ResponseBody
    public Department getById(@PathVariable("id")String id){
        return departmentService.queryData(id);
    }

    //增加
    @RequestMapping("add_judge")
    @ResponseBody
    public String add_judge(){
        return "";
    }

    @RequestMapping("add")
    public String add(){
        return "department_add";
    }

    @RequestMapping("insert")
    @ResponseBody
    public Info insertEmployee(@ModelAttribute("department") Department department){
        Info info = new Info();
        boolean b1 = departmentService.selectById(department.getDepartmentId());
        if (b1){
            info.setStatus(0);
            info.setMsg("编号不能重复！");
            return info;
        }

        boolean b2 = departmentService.selectByName(department.getDepartmentName());
        if (b2){
            info.setStatus(0);
            info.setMsg("该部门已存在！");
            return info;
        }
        boolean b = departmentService.insertDepartment(department);
        if(b){
            info.setStatus(200);
            info.setMsg("插入成功！");
        }else {
            info.setStatus(0);
            info.setMsg("插入异常！");
        }
        return info;
    }

    //删除department部分
    @RequestMapping("delete_judge")
    @ResponseBody
    public String  deleteJudge(){
        return "";
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public Info delete_batch(@ModelAttribute("ids") String[] ids) {

        Info info = new Info();
        for (String id:ids) {
            boolean b = departmentService.deleteDepartment(id);
            if (b) {
                info.setStatus(200);
                info.setMsg("删除成功");
            } else {
                info.setStatus(0);
                info.setMsg("删除失败");
            }
        }
        return info;
    }

    //修改部分
    @RequestMapping("edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "";
    }

    @RequestMapping("edit")
    public String edit(){
        return "department_edit";
    }

    @RequestMapping("update_all")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public Info updateDepartment(@ModelAttribute Department department){
        Info info = new Info();
        boolean b = departmentService.updateDepartment(department);
        if(b){
            info.setMsg("更新成功！");
            info.setStatus(200);
        }else {
            info.setMsg("更新失败！");
            info.setStatus(0);
        }
        return info;
    }

    //查询部分
    @RequestMapping("search_department_by_departmentId")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public QueryVO<Department> searchByDepartment(String searchValue, int page, int rows){
        QueryVO<Department> departmentQueryVO = departmentService.queryById(page, rows, searchValue);
        return departmentQueryVO;
    }

    @RequestMapping("search_department_by_departmentName")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public QueryVO<Department> searchByName(String searchValue, int page, int rows){
        QueryVO<Department> departmentQueryVO = departmentService.queryByName(page, rows, searchValue);
        return departmentQueryVO;
    }

}
