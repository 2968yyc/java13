package com.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: xu
 * @Date: 2019/5/17 21:20
 */
@Controller
public class EmployeeController {

    @RequestMapping("employee/find")
    public String findEmployees(){
        return "employee_list";
    }

    @RequestMapping("employee/add_judge")
    public String addEmployee(){
        return "employee_add";
    }

}
