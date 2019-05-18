package com.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: xu
 * @Date: 2019/5/17 21:44
 */
@Controller
public class DepartmentController {
    @RequestMapping("department/find")
    public String findDepartment(){
        return "department_list";
    }
}
