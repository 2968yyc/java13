package com.erp.controller.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: xu
 * @Date: 2019/5/18 9:45
 */
@Controller("employee")
public class EmployeeController {
    @RequestMapping("find")
    public String findEmployee(){
        return "employee_list";
    }
}
