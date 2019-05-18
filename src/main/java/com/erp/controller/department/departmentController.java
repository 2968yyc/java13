package com.erp.controller.department;

import com.erp.bean.QueryVO;
import com.erp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
