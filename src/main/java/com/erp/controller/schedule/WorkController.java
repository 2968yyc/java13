package com.erp.controller.schedule;

import com.erp.bean.schedule.Work;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: xf
 * @Date: 2019/5/18 15:06
 */
@Controller
@RequestMapping("work")
public class WorkController {

    @RequestMapping("get{id}")
    public List<Work> getData(){
        return null;
    }
 }
