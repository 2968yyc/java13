package com.erp.controller.quality;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("unqualify")
public class UnqualityController {

    @RequestMapping("find")
    public String find(){
        return "unqualify_list";
    }
}
