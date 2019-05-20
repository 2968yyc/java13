package com.erp.service.user.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.user.Role;
import com.erp.bean.user.RoleExample;
import com.erp.bean.user.SysUser;
import com.erp.bean.user.SysUserExample;
import com.erp.mapper.user.PermissionMapper;
import com.erp.mapper.user.RoleMapper;
import com.erp.mapper.user.SysUserMapper;

import com.erp.service.user.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yyc
 * @Date: 2019/5/17 22:11
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public boolean insertSysUser(SysUser sysUser) {
        int i = sysUserMapper.insertSelective(sysUser);
        return i!=0;
    }

    @Override
    public boolean deleteSysUser(String id) {
        int i = sysUserMapper.deleteByPrimaryKey(id);
        return i!=0;
    }

    @Override
    public boolean updateSysUser(SysUser sysUser) {
        int i = sysUserMapper.updateByPrimaryKey(sysUser);
        return i!=0;
    }

    @Override
    public boolean selectById(String id) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        return sysUser==null;
    }

    @Override
    public boolean selectByName(String name) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.or().andUsernameEqualTo(name);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        return sysUsers.size()==0;
    }

    @Override
    public QueryVO<SysUser> queryById(int page, int rows, String id) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.or().andIdLike("%"+id+"%");
        long l = sysUserMapper.countByExample(sysUserExample);
        PageHelper.startPage(page, rows);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        sysUsers = fillRoleName(sysUsers);
        return new QueryVO<>((int)l,sysUsers);
    }

    @Override
    public QueryVO<SysUser> queryByName(int page, int rows, String name) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.or().andUsernameLike("%"+name+"%");
        long l = sysUserMapper.countByExample(sysUserExample);
        PageHelper.startPage(page, rows);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        sysUsers = fillRoleName(sysUsers);
        return new QueryVO<>((int)l,sysUsers);
    }

    @Override
    public QueryVO<SysUser> searchByRoleName(int page, int rows, String searchValue) {

        searchValue = "%"+ searchValue+"%";


        RoleExample roleExample = new RoleExample();
        roleExample.or().andRoleNameLike(searchValue);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        if (roles.size()==0){
            return new QueryVO<SysUser>(0,new ArrayList<SysUser>());
        }
        List<String> list = new ArrayList<>();
        for (Role role : roles) {
            String roleId = role.getRoleId();
            list.add(roleId);
        }

        SysUserExample sysUserExample = new SysUserExample();

        sysUserExample.or().andRoleIdIn(list);

        long l = sysUserMapper.countByExample(sysUserExample);

        PageHelper.startPage(page,rows);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);


        return new QueryVO<>((int) l,sysUsers);
    }

    @Override
    public SysUser queryUserByName(String username) {
        SysUser user =  sysUserMapper.queryUserByName(username);

        return user;
    }

    @Override
    public QueryVO<SysUser> getUserInPage(int page, int rows) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.or();
        long l = sysUserMapper.countByExample(sysUserExample);
        PageHelper.startPage(page, rows);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        sysUsers = fillRoleName(sysUsers);
        return new QueryVO<>((int)l,sysUsers);
    }

    private List<SysUser> fillRoleName(List<SysUser> sysUsers) {
        for (SysUser sysUser : sysUsers) {
            String roleid = sysUser.getRoleId();
            String roleName = roleMapper.getRoleNameByRoleId(roleid);
            sysUser.setRoleName(roleName);
        }
        return sysUsers;
    }

    @Override
    public SysUser login(SysUser user) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.or().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (sysUsers.size()==0)
            return null;
        sysUsers = fillRoleName(sysUsers);
        return sysUsers.get(0);
    }


}
