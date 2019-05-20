package com.erp.controller.user;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Info;
import com.erp.bean.user.ActiveUser;
import com.erp.bean.user.SysUser;
import com.erp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yyc
 * @Date: 2019/5/17 21:55
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("find")
    public String userpage(){
        return "user_list";
    }

    @RequestMapping("list")
    public @ResponseBody QueryVO userList(int page,int rows){
        return userService.getUserInPage(page,rows);
    }


    @RequestMapping("add_judge")
    @ResponseBody
    public String add_judge(){
        return "";
    }

    @RequestMapping("add")
    public String add(){
        return "user_add";
    }

    @RequestMapping("insert")
    @ResponseBody
    public Info insertEmployee(@ModelAttribute SysUser sysUser){
        Info info = new Info();
        boolean b1 = userService.selectById(sysUser.getId());
        if (!b1){
            info.setStatus(0);
            info.setMsg("该用户编号已经存在，请更换用户编号！");
            return info;
        }
        boolean b2 = userService.selectByName(sysUser.getUsername());
        if (!b2){
            info.setStatus(0);
            info.setMsg("该用户名已经存在，请更换用户名！");
            return info;
        }
        boolean b = userService.insertSysUser(sysUser);
        if(b){
            info.setStatus(200);
            info.setMsg("新增用户信息成功！");
        }else {
            info.setStatus(0);
            info.setMsg("提交异常！请您再次提交");
        }
        return info;
    }

    //删除部分
    @RequestMapping("delete_judge")
    @ResponseBody
    public String  deleteJudge(){
        return "";
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public Info delete_batch(@ModelAttribute("ids") String[] ids) {

        Info info = new Info();
        for (String id:ids) {
            boolean b = userService.deleteSysUser(id);
            if (b) {
                info.setStatus(200);
                info.setMsg("删除成功");
            } else {
                info.setStatus(0);
                info.setMsg("删除失败");
            }
        }
        return info;
    }

    //修改部分
    @RequestMapping("edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "";
    }

    @RequestMapping("edit")
    public String edit(){
        return "user_edit";
    }

    @RequestMapping("update_all")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public Info updateUser(@ModelAttribute SysUser sysUser){
        Info info = new Info();
        boolean b = userService.updateSysUser(sysUser);
        if(b){
            info.setMsg("更新成功！");
            info.setStatus(200);
        }else {
            info.setMsg("更新失败！");
            info.setStatus(0);
        }
        return info;
    }

    //查询部分
    @RequestMapping("search_user_by_userId")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public QueryVO<SysUser> searchById(String searchValue, int page, int rows){
        QueryVO<SysUser> sysUserQueryVO = userService.queryById(page, rows, searchValue);
        return sysUserQueryVO;
    }

    @RequestMapping("search_user_by_userName")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public QueryVO<SysUser> searchByName(String searchValue, int page, int rows){
        QueryVO<SysUser> sysUserQueryVO = userService.queryByName(page,rows,searchValue);
        return sysUserQueryVO;
    }

    @RequestMapping("search_user_by_roleName")
    @ResponseBody
    public QueryVO<SysUser> searchByRoleName(String searchValue, int page, int rows){
        QueryVO<SysUser> sysUserQueryVO = userService.searchByRoleName(page,rows,searchValue);
        return sysUserQueryVO;
    }


    @RequestMapping("role")
    public String toRole(){
        return "role_add";
    }


}
