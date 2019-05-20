package com.erp.controller.user;

import com.erp.bean.device.Info;
import com.erp.bean.user.ActiveUser;
import com.erp.bean.user.Permission;
import com.erp.bean.user.SysUser;
import com.erp.service.user.PermissionService;
import com.erp.service.user.UserService;
import com.erp.utils.PermissionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping("ajaxQuery")
    @ResponseBody
    public Info query(String username){

        SysUser user = userService.queryUserByName(username);


        System.out.printf("query"+user.getRoleId());

        return new Info(200,user.getRoleId(),null);


    }

    @RequestMapping("ajaxLogin")
    public @ResponseBody
    Info login(SysUser user, String remember, String randomcode , HttpServletRequest request){
        //验证码
        HttpSession session = request.getSession();
        if (randomcode!=null){
            String validateCode = (String) session.getAttribute("validateCode");
            if (!validateCode.equals(randomcode)){
                //没输入或瞎几把输入验证码
                return new Info(0,"randomcode_error",null);
            }
        }
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
                Map<String, String> prefix = PermissionUtils.getMap();

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
