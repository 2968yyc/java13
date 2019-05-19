package com.erp.service.employee.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.employee.Employee;
import com.erp.bean.employee.EmployeeExample;
import com.erp.mapper.employee.EmployeeMapper;
import com.erp.service.employee.EmployeeService;
import com.github.pagehelper.PageHelper;
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
        PageHelper.startPage(page, rows);
        List<Employee> employees = employeeMapper.selectEmployees();
        return new QueryVO(length,employees);
    }

    @Override
    public boolean deleteEmployee(String id) {
        int i = employeeMapper.deleteByPrimaryKey(id);
        return i!=0;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        int i = employeeMapper.updateByPrimaryKey(employee);
        return i==1;
    }

    @Override
    public QueryVO<Employee> queryByID(int page, int rows, String id) {
        PageHelper.startPage(page,rows);
        List<Employee> employees = employeeMapper.queryById(id);
        int length = employees.size();
        return new QueryVO<>(length,employees);
    }

    @Override
    public QueryVO<Employee> queryByName(int page, int rows, String name) {
        PageHelper.startPage(page,rows);
        List<Employee> employees = employeeMapper.queryByName(name);
        int length = employees.size();
        return new QueryVO<>(length,employees);
    }

    @Override
    public QueryVO<Employee> queryByDepartment(int page, int rows,String department) {
        PageHelper.startPage(page,rows);
        List<Employee> employees = employeeMapper.selectByDepartment(department);
        int length = employees.size();
        return new QueryVO<>(length,employees);
    }

    @Override
    public Employee getDataById(String id) {
        Employee employee = employeeMapper.selectByID(id);
        return employee;
    }

    @Override
    public boolean insertEmployee(Employee employee) {
        int insert = employeeMapper.insert(employee);
        return insert!=0;
    }

}
