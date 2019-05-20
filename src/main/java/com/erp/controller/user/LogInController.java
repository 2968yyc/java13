package com.erp.controller.user;

import com.erp.bean.device.Info;
import com.erp.bean.user.ActiveUser;
import com.erp.bean.user.Permission;
import com.erp.bean.user.SysUser;
import com.erp.service.user.PermissionService;
import com.erp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Author: yyc
 * @Date: 2019/5/19 23:51
 */
@Controller
public class LogInController {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;


    @RequestMapping("/")
    public String tologin(){
        return "login";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request){

        HttpSession session = request.getSession();
        session.invalidate();

        return "login";

    }

    @RequestMapping("ajaxLogin")
    public @ResponseBody
    Info login(SysUser user, HttpServletRequest request){
        //Todo 验证码校验
        boolean b = userService.selectByName(user.getUsername());
        if (b){
            return new Info(0,"account_error",null);
        }
        SysUser sysUser = userService.login(user);
        if (sysUser==null){
            return new Info(0,"password_error",null);
        }else if ("2".equals(sysUser.getLocked())){
            return new Info(0,"authentication_error",null);
        }
        else{
            HttpSession session = request.getSession();
            Set<String> sysPermissionList = (Set<String>) session.getAttribute("sysPermissionList");
            if (sysPermissionList==null){
                sysPermissionList=new LinkedHashSet<>();
            }
            //解析permission字符串
            Permission permission = permissionService.getPermissionByRoleId(sysUser.getRoleId());
            String permissionId = permission.getSysPermissionId();
            if (permissionId.length()>=2){
                String[] perms = permissionId.split(",");
                String[] suffix = new String[]{"", ":add", ":edit", ":delete"};
                Map<String, String> prefix = new HashMap<>();
                prefix.put("1", "order");
                prefix.put("2", "custom");
                prefix.put("3", "product");
                prefix.put("6", "work");
                prefix.put("7", "manufacture");
                prefix.put("8", "task");
                prefix.put("9", "technology");
                prefix.put("10", "process");
                prefix.put("11", "technologyPlan");
                prefix.put("12", "technologyRequirement");
                prefix.put("18", "material");
                prefix.put("19", "materialReceive");
                prefix.put("22", "materialConsume");
                prefix.put("13", "fCountCheck");
                prefix.put("14", "fMeasureCheck");
                prefix.put("15", "pCountCheck");
                prefix.put("16", "pMeasureCheck");
                prefix.put("17", "unqualify");
                prefix.put("23", "device");
                prefix.put("27", "deviceType");
                prefix.put("24", "deviceCheck");
                prefix.put("25", "deviceFault");
                prefix.put("26", "deviceMaintain");
                prefix.put("4", "department");
                prefix.put("5", "employee");
                prefix.put("20", "user");
                prefix.put("21", "role");

                for (String perm : perms) {
                    String[] strings = splitLastChar(perm);
                    String pre = prefix.get(strings[0]);
                    int i = Integer.parseInt(strings[1]);
                    sysPermissionList.add(pre + suffix[i]);
                }
            }

            session.setAttribute("sysPermissionList",sysPermissionList);
            session.setAttribute("activeUser",new ActiveUser(sysUser.getUsername(),sysUser.getRoleName()));
            return new Info(200,"success",null);


        }
    }

    private String[] splitLastChar(String s){
        int length = s.length();
        return new String[]{s.substring(0,length-1),s.substring(length-1)};
    }
}
