package com.erp.controller.department;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: xu
 * @Date: 2019/5/18 9:49
 */
@Controller("department")
public class departmentController {
    @RequestMapping("find")
    public String findDepartment(){
        return "department_list";
    }

}
