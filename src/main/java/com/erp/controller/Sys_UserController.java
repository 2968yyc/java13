package com.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: xu
 * @Date: 2019/5/17 22:13
 */
@Controller
public class Sys_UserController {
    @RequestMapping("user/find")
    public String findUser(){
        return "user_list";
    }
}
