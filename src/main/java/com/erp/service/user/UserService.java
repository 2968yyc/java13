package com.erp.service.user;

import com.erp.bean.QueryVO;
import com.erp.bean.user.SysUser;

/**
 * @Author: yyc
 * @Date: 2019/5/17 22:10
 */
public interface UserService {
    SysUser login(SysUser user);
    QueryVO<SysUser> getUserInPage(int page,int rows);
    boolean insertSysUser(SysUser sysUser);
    boolean deleteSysUser(String id);
    boolean updateSysUser(SysUser sysUser);
    boolean selectById(String id);
    boolean selectByName(String name);
    QueryVO<SysUser> queryById(int page,int rows,String id);
    QueryVO<SysUser> queryByName(int page,int rows,String name);

    QueryVO<SysUser> searchByRoleName(int page, int rows, String searchValue);
}
