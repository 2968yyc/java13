package com.erp.service.user;

import com.erp.bean.user.Permission;

/**
 * @Author: yyc
 * @Date: 2019/5/19 22:43
 */
public interface PermissionService {
    Permission getPermissionByRoleId(String roleId);

    int updateByRoleId(String roleId, String permission);
}
