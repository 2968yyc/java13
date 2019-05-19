package com.erp.controller.user;

import com.erp.bean.device.Info;
import com.erp.bean.user.Permission;
import com.erp.service.user.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: yyc
 * @Date: 2019/5/19 22:38
 */
@Controller
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("get_permission")
    public @ResponseBody
    Permission getPermissionByRoleId(String roleId){
        return permissionService.getPermissionByRoleId(roleId);
    }
    @RequestMapping("update_by_roleid")
    public @ResponseBody
    Info updateByRoleId(String roleId,String permission){
        int res = permissionService.updateByRoleId(roleId,permission);
        if (res==1){
            return new Info(200,"权限更新成功",null);
        }else{
            return new Info(0,"权限更新失败，请稍后再来",null);
        }
    }
}
