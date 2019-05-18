package com.erp.controller.quality;

import com.erp.bean.QueryVO;
import com.erp.service.quality.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:ZCH
 * @Date:2019/5/18 11:04
 */
@Controller
@RequestMapping("p_measure_check")
public class MeasurCheckController {

    @Autowired
    MeasureService measureService;

    @RequestMapping("list")
    @ResponseBody
    public QueryVO list(int page, int rows){
        return measureService.selectPageMeasure(page, rows);
    }

    @RequestMapping("add")
    public String add(){
        return "p_measure_check_add";
    }
}
