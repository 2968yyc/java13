package com.erp.controller.quality;

import com.erp.service.UnqualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("unqualify")
public class UnqualityController {

    @Autowired
    UnqualityService unqualityService;

    @RequestMapping("find")
    public String find(Model model){
        return "unqualify_list";
    }
    /*public List<Unqualify> findAllUnquality(){
        List<Unqualify> unqualifies = unqualityService.selectAllUnqualify();
        return unqualifies;
    }*/
}
