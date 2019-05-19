package com.erp.service.employee;

import com.erp.bean.QueryVO;
import com.erp.bean.employee.Employee;

import java.util.List;

/**
 * @Author: xu
 * @Date: 2019/5/18 10:30
 */
public interface EmployeeService {
    QueryVO<Employee> getEmployeeInPage(int page,int rows);
    List<Employee> getData();
    Employee getDataById(String id);
    boolean insertEmployee(Employee employee);
    boolean deleteEmployee(String id);
    boolean updateEmployee(Employee employee);
    QueryVO<Employee> queryByDepartment(int page,int rows,String department);
    QueryVO<Employee> queryByID(int page,int rows,String id);
    QueryVO<Employee> queryByName(int page,int rows,String name);

}
