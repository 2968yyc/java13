package com.erp.service.employee;

import com.erp.bean.QueryVO;
import com.erp.bean.employee.Employee;

import java.util.List;

/**
 * @Author: xu
 * @Date: 2019/5/18 10:30
 */
public interface EmployeeService {
    QueryVO getEmployeeInPage(int page,int rows);
    List<Employee> getData();
    Employee getDataById(String id);
}
