package com.erp.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: yyc
 * @Date: 2019/5/20 10:57
 * 静态内部类单例map,和一些解析字符串方法
 */
public class PermissionUtils {
    private PermissionUtils(){}
    private static class Inner{
        private static Map<String, String> prefix = new HashMap<>();
        static{
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
        }
    }
    //String[] suffix = new String[]{"", ":add", ":edit", ":delete"};

    public static Map<String, String> getMap(){
        return Inner.prefix;
    }

    public static String[] splitLastChar(String s){
        int length = s.length();
        return new String[]{s.substring(0,length-1),s.substring(length-1)};
    }

    public static Map<String,String> permissionCheck(String permission_str, HttpServletRequest request){
        Map<String,String> res = new HashMap<>();
        HttpSession session = request.getSession();
        Set<String> sysPermissionList = (Set<String>) session.getAttribute("sysPermissionList");
        if (sysPermissionList==null || sysPermissionList.size()==0 ||!sysPermissionList.contains(permission_str)) {
            //没权限或没登录
            res.put("msg", "您没有这个权限！");
        }
        return res;
    }

}
