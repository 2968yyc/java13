package com.erp.service.employee.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.employee.Employee;
import com.erp.bean.employee.EmployeeExample;
import com.erp.mapper.employee.EmployeeMapper;
import com.erp.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xu
 * @Date: 2019/5/18 10:39
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getData() {
        List<Employee> employees = employeeMapper.selectEmployees();
        return employees;
    }


    @Override
    public QueryVO getEmployeeInPage(int page, int rows) {
        int length = employeeMapper.countAll();
//        PageHelper.startPage(page, rows);
//        EmployeeExample employeeExample = new EmployeeExample();
//        employeeExample.or();
        List<Employee> employees = employeeMapper.selectEmployees();
        System.out.println(employees);
        return new QueryVO(length,employees);
    }

    @Override
    public Employee getDataById(String id) {
        Employee employee = employeeMapper.selectByID(id);
        return employee;
    }


}
