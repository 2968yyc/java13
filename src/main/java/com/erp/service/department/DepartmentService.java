package com.erp.service.department;

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
    boolean insertDepartment(Department department);
    boolean deleteDepartment(String id);
    boolean updateDepartment(Department department);
    boolean selectById(String id);
    boolean selectByName(String name);
    QueryVO<Department> queryById(int page,int rows,String id);
    QueryVO<Department> queryByName(int page,int rows,String name);
    Department getDataById(String id);
}
