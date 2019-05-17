package com.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Qiu
 * @Date: 2019/5/17 13:10
 */

@Controller
public class MainController {

    @RequestMapping("home")
    public String toHome(){
        System.out.println(111);
        return "home";
    }

}
