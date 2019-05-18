package com.erp.controller.quality;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:ZCH
 * @Date:2019/5/18 11:24
 */

@Controller
@RequestMapping("pMeasureCheck")
public class PMeasureCheckController {

    @RequestMapping("add_judge")
    @ResponseBody
    public String add_judge(){
        return "";
    }
}
