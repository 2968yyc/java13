package com.erp.controller.user;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Info;
import com.erp.bean.user.ActiveUser;
import com.erp.bean.user.SysUser;
import com.erp.service.UserService;
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
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/")
    public String tologin(){
        return "login";
    }

    @RequestMapping("ajaxLogin")
    public @ResponseBody Info login(SysUser user, HttpServletRequest request){
        boolean login = userService.login(user);
        if (!login){
            //TODO 'password_error authentication_error
            return new Info(0,"account_error",null);
        }else{
            //TODO 所有人都是超级管理员，以后修改
            HttpSession session = request.getSession();
            List<String> sysPermissionList = (List<String>) session.getAttribute("sysPermissionList");
            if (sysPermissionList==null){
                sysPermissionList=new ArrayList<String>();
            }
            sysPermissionList.add("device:add");
            sysPermissionList.add("device:edit");
            sysPermissionList.add("device:delete");
            sysPermissionList.add("deviceType:add");
            sysPermissionList.add("deviceType:edit");
            sysPermissionList.add("deviceType:delete");
            sysPermissionList.add("deviceCheck:add");
            sysPermissionList.add("deviceCheck:edit");
            sysPermissionList.add("deviceCheck:delete");
            sysPermissionList.add("deviceFault:add");
            sysPermissionList.add("deviceFault:edit");
            sysPermissionList.add("deviceFault:delete");
            sysPermissionList.add("deviceMaintain:add");
            sysPermissionList.add("deviceMaintain:edit");
            sysPermissionList.add("deviceMaintain:delete");

            //这里就按这样写
            sysPermissionList.add("technology:add");
            sysPermissionList.add("technology:edit");
            sysPermissionList.add("technology:delete");
            //部门
            sysPermissionList.add("department:add");
            sysPermissionList.add("department:edit");
            sysPermissionList.add("department:delete");
            //人员
            sysPermissionList.add("employee:add");
            sysPermissionList.add("employee:edit");
            sysPermissionList.add("employee:delete");
            //管理
            sysPermissionList.add("user:add");
            sysPermissionList.add("user:edit");
            sysPermissionList.add("user:delete");

            sysPermissionList.add("technologyPlan:delete");
            sysPermissionList.add("technologyPlan:edit");
            sysPermissionList.add("technologyPlan:add");

            sysPermissionList.add("technologyRequirement:add");
            sysPermissionList.add("technologyRequirement:edit");
            sysPermissionList.add("technologyRequirement:delete");

            //工序
            sysPermissionList.add("process:add");
            sysPermissionList.add("process:edit");
            sysPermissionList.add("process:delete");


            //schedule
            sysPermissionList.add("custom:add");
            sysPermissionList.add("custom:edit");
            sysPermissionList.add("custom:delete");
            sysPermissionList.add("work:add");
            sysPermissionList.add("work:edit");
            sysPermissionList.add("work:delete");
            sysPermissionList.add("product:add");
            sysPermissionList.add("product:edit");
            sysPermissionList.add("product:delete");
            sysPermissionList.add("task:add");
            sysPermissionList.add("task:edit");
            sysPermissionList.add("task:delete");
            sysPermissionList.add("manufacture:add");
            sysPermissionList.add("manufacture:edit");
            sysPermissionList.add("manufacture:delete");
            sysPermissionList.add("order:add");
            sysPermissionList.add("order:edit");
            sysPermissionList.add("order:delete");


            sysPermissionList.add("material:add");
            sysPermissionList.add("material:edit");
            sysPermissionList.add("material:delete");

            sysPermissionList.add("materialReceive:add");
            sysPermissionList.add("materialReceive:edit");
            sysPermissionList.add("materialReceive:delete");

            sysPermissionList.add("materialConsume:add");
            sysPermissionList.add("materialConsume:edit");
            sysPermissionList.add("materialConsume:delete");


            sysPermissionList.add("unqualify:add");
            sysPermissionList.add("unqualify:edit");
            sysPermissionList.add("unqualify:delete");

            sysPermissionList.add("pMeasureCheck:add");
            sysPermissionList.add("pMeasureCheck:edit");
            sysPermissionList.add("pMeasureCheck:delete");

            session.setAttribute("sysPermissionList",sysPermissionList);
            session.setAttribute("activeUser",new ActiveUser("aa","超级管理员"));
            return new Info(1,"success",null);

        }
    }

    @RequestMapping("user/find")
    public String userpage(){
        return "user_list";
    }

    @RequestMapping("user/list")
    public @ResponseBody QueryVO userList(int page,int rows){
        return userService.getUserInPage(page,rows);
    }


    @RequestMapping("user/add_judge")
    @ResponseBody
    public String add_judge(){
        return "";
    }

    @RequestMapping("user/add")
    public String add(){
        return "user_add";
    }

    @RequestMapping("user/insert")
    @ResponseBody
    public Info insertEmployee(@ModelAttribute SysUser sysUser){
        Info info = new Info();
        boolean b1 = userService.selectById(sysUser.getId());
        if (b1){
            info.setStatus(0);
            info.setMsg("编号不能重复！");
            return info;
        }

        boolean b = userService.insertSysUser(sysUser);
        if(b){
            info.setStatus(200);
            info.setMsg("插入成功！");
        }else {
            info.setStatus(0);
            info.setMsg("插入异常！");
        }
        return info;
    }

    //删除部分
    @RequestMapping("user/delete_judge")
    @ResponseBody
    public String  deleteJudge(){
        return "";
    }

    @RequestMapping("user/delete_batch")
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
    @RequestMapping("user/edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "";
    }

    @RequestMapping("user/edit")
    public String edit(){
        return "user_edit";
    }

    @RequestMapping("user/update_all")
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
    @RequestMapping("user/search_user_by_userId")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public QueryVO<SysUser> searchById(String searchValue, int page, int rows){
        QueryVO<SysUser> sysUserQueryVO = userService.queryById(page, rows, searchValue);
        return sysUserQueryVO;
    }

    @RequestMapping("user/search_user_by_userName")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public QueryVO<SysUser> searchByName(String searchValue, int page, int rows){
        QueryVO<SysUser> sysUserQueryVO = userService.queryByName(page,rows,searchValue);
        return sysUserQueryVO;
    }


}
