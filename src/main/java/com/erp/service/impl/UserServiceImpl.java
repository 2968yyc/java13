package com.erp.service.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.user.SysUser;
import com.erp.bean.user.SysUserExample;
import com.erp.mapper.user.SysUserMapper;
import com.erp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yyc
 * @Date: 2019/5/17 22:11
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public QueryVO<SysUser> getUserInPage(int page, int rows) {
        int length = sysUserMapper.countAll();
        List<SysUser> sysUsers = sysUserMapper.selectSysUsers();
        return new QueryVO(length,sysUsers);
    }

    @Override
    public boolean login(SysUser user) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.or().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        return sysUsers.size() == 1;
    }
}
