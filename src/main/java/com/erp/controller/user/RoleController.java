package com.erp.controller.user;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Info;
import com.erp.bean.user.Role;
import com.erp.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: yyc
 * @Date: 2019/5/19 22:30
 */
@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("get/{id}")
    public @ResponseBody
    Role getRoleById(@PathVariable("id")String id){
        return roleService.getRoleById(id);
    }
    @RequestMapping("get_data")
    public @ResponseBody
    List<Role> getAllRole(){
        return roleService.getAllRole();
    }

    @RequestMapping("find")
    public String toRoleList(){
        return "role_list";
    }
    @RequestMapping("list")
    public @ResponseBody
    QueryVO userList(int page, int rows){
        return roleService.getRoleInPage(page,rows);
    }
    @RequestMapping("permission")
    public String toPermission(){
        return "permission";
    }
    @RequestMapping("add_judge")
    public @ResponseBody String  addDudge(){
        //Todo 判断权限
        return "";
    }
    @RequestMapping("add")
    public String toAdd(){
        return "role_add";
    }

    @RequestMapping("update_all")
    @ResponseBody
    public Info addRole(Role role,String permission){
        if ("".equals(role.getRoleId())) {
            //添加新角色
            if (roleService.getRoleByName(role.getRoleName())!=null){
                return new Info(0,"添加新角色失败！已有该角色名",null);
            }
            int res = roleService.addRole(role, permission);
            if (res == 1) {
                return new Info(200, "添加新角色成功！", null);
            } else {
                return new Info(0, "添加新角色失败！请稍后在来", null);
            }
        }else{
            //更改新角色
            int res = roleService.updateRole(role, permission);
            if (res == 1) {
                return new Info(200, "修改角色成功！", null);
            } else {
                return new Info(0, "修改角色失败！请稍后在来", null);
            }
        }
    }
    @RequestMapping("edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "";
    }

    @RequestMapping("edit")
    public String edit(){
        return "role_edit";
    }


    @RequestMapping("delete_judge")
    @ResponseBody
    public String  deleteJudge(){
        return "";
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    public Info delete_batch(@ModelAttribute("ids") String[] ids) {
        int res = roleService.deleteBatch(ids);
        if (res==1){
            return new Info(200,"刪除成功！",null);
        }
        return new Info(0,"删除失败！",null);
    }
    @RequestMapping("search_role_by_roleId")
    public @ResponseBody
    QueryVO searchRoleByRoldId(int page, int rows,String searchValue){
        return roleService.searchRoleByRoldId(page,rows,searchValue);
    }
    @RequestMapping("search_role_by_roleName")
    public @ResponseBody
    QueryVO searchRoleByRoldName(int page, int rows,String searchValue){
        return roleService.searchRoleByRoldName(page,rows,searchValue);
    }

}
