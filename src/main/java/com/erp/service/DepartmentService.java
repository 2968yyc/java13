package com.erp.service;

import com.erp.bean.QueryVO;

/**
 * @Author: xu
 * @Date: 2019/5/18 12:45
 */
public interface DepartmentService {
    QueryVO getDepartmentInPage(int page, int rows);
}
