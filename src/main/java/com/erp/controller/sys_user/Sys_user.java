package com.erp.controller.sys_user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: xu
 * @Date: 2019/5/18 9:53
 */
@Controller("user")
public class Sys_user {
    @RequestMapping("find")
    public String findUser(){
        return "user_list";
    }
}
