package com.erp.controller.quality;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Info;
import com.erp.bean.quality.Unqualify;
import com.erp.service.quality.UnqualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("insert")
    public String insert(Unqualify unqualify, Model model){
        boolean flag = unqualityService.insertUnqualify(unqualify);
        return "unqualify_add";
    }

    @RequestMapping("delete_judge")
    @ResponseBody
    public String delete_judge(){
        return "";
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    public Info delete_batch(String[] ids){
        boolean flag = unqualityService.deleteUnqualifyById(ids);
        if (flag){
            return new Info(200,"OK",null);
        }else {
            return new Info(0,"error",null);
        }

    }
}
