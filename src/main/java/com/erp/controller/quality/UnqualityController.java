package com.erp.controller.quality;

import com.erp.bean.QueryVO;
import com.erp.service.UnqualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("unqualify")
public class UnqualityController {

    @Autowired
    UnqualityService unqualityService;

    @RequestMapping("find")
    public String find(){
        return "unqualify_list";
    }

    @RequestMapping("list")
    @ResponseBody
    public QueryVO list(int page, int rows){
        return  unqualityService.selectPageUnqualify(page, rows);
    }

    @RequestMapping("add_judge")
    @ResponseBody
    public String add_judge(){
        return "";
    }

    @RequestMapping("add")
    public String add(){
        return "unqualify_add";
    }
}
