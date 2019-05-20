package com.erp.service.department.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.department.Department;
import com.erp.bean.department.DepartmentExample;
import com.erp.mapper.department.DepartmentMapper;
import com.erp.service.department.DepartmentService;
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
    public boolean selectByName(String name) {
        List<Department> departments = departmentMapper.queryByName(name);
        return departments.size()!=0;
    }

    @Override
    public boolean selectById(String id) {
        List<Department> departments = departmentMapper.queryById(id);
        return departments.size()!=0;
    }

    @Override
    public boolean insertDepartment(Department department) {
        int insert = departmentMapper.insert(department);
        return insert!=0;
    }

    @Override
    public QueryVO<Department> queryById(int page,int rows,String id) {
        PageHelper.startPage(page,rows);
        List<Department> departments = departmentMapper.queryById(id);
        int i = departments.size();
        return new QueryVO<Department>(i,departments);
    }

    @Override
    public Department getDataById(String id) {
        List<Department> departments = departmentMapper.queryById(id);
        return departments.get(0);
    }

    @Override
    public QueryVO<Department> queryByName(int page,int rows,String name) {
        PageHelper.startPage(page,rows);
        List<Department> departments = departmentMapper.queryByName(name);
        int i = departments.size();
        return new QueryVO<Department>(i,departments);
    }

    @Override
    public boolean deleteDepartment(String id) {
        int i = departmentMapper.deleteByPrimaryKey(id);
        return i!=0;
    }

    @Override
    public boolean updateDepartment(Department department) {
        int i = departmentMapper.updateByPrimaryKey(department);
        return i!=0;
    }

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
