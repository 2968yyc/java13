package com.erp.controller.quality;

import com.erp.bean.quality.FinalMeasuret;
import com.erp.service.quality.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:ZCH
 * @Date:2019/5/18 8:57
 */

@Controller
@RequestMapping("measure")
public class MeasureController {

    @Autowired
    MeasureService measureService;

    @RequestMapping("find")
    public String find(){
        return "p_measure_check_list";
    }

    @RequestMapping("insert")
    public String insert(FinalMeasuret finalMeasuret){
        return "p_measure_check_list";
    }
}
