package com.erp.controller.employee;

import com.erp.bean.QueryVO;
import com.erp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: xu
 * @Date: 2019/5/18 9:45
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("list")
    public @ResponseBody
    QueryVO findList(int page, int rows){
        return employeeService.getEmployeeInPage(page,rows);
    }

    @RequestMapping("find")
    public String findEmployee(){
        return "employee_list";
    }

    @RequestMapping("add_judge")
    public String add_judge(){
        return "employee_add";
    }

    @RequestMapping("add")
    public String add(){
        return "employee_add";
    }

    @RequestMapping("delete_judge")
    public String delete(){
        return null;
    }

    @RequestMapping("edit_judge")
    public String edit(){
        return "employee_edit";
    }

}
