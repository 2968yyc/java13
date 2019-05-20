package com.erp.service.user;

import com.erp.bean.QueryVO;
import com.erp.bean.user.Role;

import java.util.List;

/**
 * @Author: yyc
 * @Date: 2019/5/19 22:33
 */
public interface RoleService {
    Role getRoleById(String roldId);

    List<Role> getAllRole();

    QueryVO getRoleInPage(int page, int rows);

    int addRole(Role role, String permission);

    Role getRoleByName(String roleName);

    int updateRole(Role role, String permission);

    int deleteBatch(String[] ids);

    QueryVO searchRoleByRoldId(int page, int rows, String searchValue);

    QueryVO searchRoleByRoldName(int page, int rows, String searchValue);
}
