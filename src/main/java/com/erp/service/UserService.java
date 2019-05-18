package com.erp.service;

import com.erp.bean.QueryVO;
import com.erp.bean.user.SysUser;

/**
 * @Author: yyc
 * @Date: 2019/5/17 22:10
 */
public interface UserService {
    boolean login(SysUser user);
    QueryVO<SysUser> getUserInPage(int page,int rows);

}
