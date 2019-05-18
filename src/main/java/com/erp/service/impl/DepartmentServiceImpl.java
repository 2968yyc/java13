package com.erp.service.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.department.Department;
import com.erp.bean.department.DepartmentExample;
import com.erp.bean.employee.Employee;
import com.erp.bean.employee.EmployeeExample;
import com.erp.mapper.department.DepartmentMapper;
import com.erp.mapper.employee.EmployeeMapper;
import com.erp.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xu
 * @Date: 2019/5/18 12:46
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepartmentData() {
        DepartmentExample departmentExample = new DepartmentExample();
        departmentExample.or();
        List<Department> departments = departmentMapper.selectByExample(departmentExample);
        return departments;
    }

    @Override
    public QueryVO getDepartmentInPage(int page, int rows) {
        int length = departmentMapper.countAll();
        PageHelper.startPage(page, rows);
        DepartmentExample departmentExample = new DepartmentExample();
        departmentExample.or();
        List<Department> departments = departmentMapper.selectByExample(departmentExample);
        return new QueryVO(length,departments);
    }
}
