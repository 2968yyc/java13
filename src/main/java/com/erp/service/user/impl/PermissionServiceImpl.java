package com.erp.service.user.impl;

import com.erp.bean.user.Permission;
import com.erp.bean.user.PermissionExample;
import com.erp.mapper.user.PermissionMapper;
import com.erp.service.user.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yyc
 * @Date: 2019/5/19 22:43
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Permission getPermissionByRoleId(String roleId) {
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.or().andSysRoleIdEqualTo(roleId);
        List<Permission> permissions = permissionMapper.selectByExample(permissionExample);
        if (permissions.size()==0)
            return null;
        return permissions.get(0);
    }

    @Override
    public int updateByRoleId(String roleId, String permission) {
        return permissionMapper.updatePermissionByRoleId(permission,roleId);
    }
}
