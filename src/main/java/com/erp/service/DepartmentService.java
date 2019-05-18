package com.erp.service;

import com.erp.bean.QueryVO;
import com.erp.bean.department.Department;

import java.util.List;

/**
 * @Author: xu
 * @Date: 2019/5/18 12:45
 */
public interface DepartmentService {
    QueryVO getDepartmentInPage(int page, int rows);
    List<Department> getDepartmentData();

}
