package com.erp.service;

import com.erp.bean.QueryVO;

/**
 * @Author: xu
 * @Date: 2019/5/18 10:30
 */
public interface EmployeeService {
    QueryVO getEmployeeInPage(int page,int rows);
}
