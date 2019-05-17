package com.erp.controller.quality;

import com.erp.bean.quality.Unqualify;
import com.erp.service.UnqualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UnqualityController {

    @Autowired
    UnqualityService unqualityService;

    @RequestMapping("unqualify/find")
    public String findAllUnquality(Model model){
        List<Unqualify> unqualifies = unqualityService.findAllUnqualify();
        model.addAttribute("unqualifies", unqualifies);
        return "unqualify_list.jsp";
    }
}
