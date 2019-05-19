package com.erp.service.user.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.user.Permission;
import com.erp.bean.user.Role;
import com.erp.bean.user.RoleExample;
import com.erp.mapper.user.PermissionMapper;
import com.erp.mapper.user.RoleMapper;
import com.erp.service.user.RoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

/**
 * @Author: yyc
 * @Date: 2019/5/19 22:33
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Role getRoleById(String roldId) {
        return roleMapper.selectByPrimaryKey(roldId);
    }

    @Override
    public List<Role> getAllRole() {
        RoleExample roleExample = new RoleExample();
        roleExample.or();
        return  roleMapper.selectByExample(roleExample);
    }

    @Override
    public QueryVO getRoleInPage(int page, int rows) {
        RoleExample roleExample = new RoleExample();
        roleExample.or();
        long l = roleMapper.countByExample(roleExample);
        PageHelper.startPage(page, rows);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        roles = fillPermission(roles);
        return new QueryVO((int)l,roles);
    }

    @Override
    public int addRole(Role role, String permission) {
        UUID uuid = UUID.randomUUID();
        role.setRoleId(uuid.toString());
        int insert = roleMapper.insert(role);
        Permission perm = new Permission();
        perm.setId(UUID.randomUUID().toString());
        perm.setSysPermissionId(permission);
        perm.setSysRoleId(uuid.toString());
        int insert1 = permissionMapper.insert(perm);
        return (insert+insert1)==2?1:0;


    }

    @Override
    public Role getRoleByName(String roleName) {
        RoleExample roleExample = new RoleExample();
        roleExample.or().andRoleNameEqualTo(roleName);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        if (roles.size()==0)
            return null;
        return roles.get(0);
    }

    @Override
    public int updateRole(Role role, String permission) {
        int update = roleMapper.updateByPrimaryKey(role);
        int i = permissionMapper.updatePermissionByRoleId(permission, role.getRoleId());
        return (update+i)==2?1:0;
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    @Override
    public int deleteBatch(String[] ids) {
        for (String id : ids) {
            int i = roleMapper.deleteByPrimaryKey(id);
            if (i!=1)
                throw new RuntimeException("删除role失败id为"+id);
        }
        return 1;
    }

    @Override
    public QueryVO searchRoleByRoldId(int page, int rows, String searchValue) {
        RoleExample roleExample = new RoleExample();
        roleExample.or().andRoleIdLike("%"+searchValue+"%");
        long l = roleMapper.countByExample(roleExample);
        PageHelper.startPage(page,rows);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        roles = fillPermission(roles);
        return new QueryVO((int)l,roles);
    }

    @Override
    public QueryVO searchRoleByRoldName(int page, int rows, String searchValue) {
        RoleExample roleExample = new RoleExample();
        roleExample.or().andRoleNameLike("%"+searchValue+"%");
        long l = roleMapper.countByExample(roleExample);
        PageHelper.startPage(page,rows);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        roles = fillPermission(roles);
        return new QueryVO((int)l,roles);
    }

    private List<Role> fillPermission(List<Role> roles) {
        for (Role role : roles) {
            String roleId = role.getRoleId();
            String permissionId = permissionMapper.getPermissionIdByRoleId(roleId);
            role.setSysPermissionId(permissionId);
        }
        return roles;
    }
}
