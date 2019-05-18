package com.erp.controller.quality;

import com.erp.bean.QueryVO;
import com.erp.bean.quality.FinalMeasuret;
import com.erp.service.quality.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:ZCH
 * @Date:2019/5/18 8:57
 */

@Controller
public class MeasureController {

    @Autowired
    MeasureService measureService;

    /*----------------------------以下是分页查询功能----------------------------*/

    @RequestMapping("measure/find")
    public String find(){
        return "p_measure_check_list";
    }

    @RequestMapping("p_measure_check/list")
    @ResponseBody
    public QueryVO list(int page, int rows){
        return measureService.selectPageMeasure(page, rows);
    }

    /*----------------------------以下是添加功能----------------------------*/

    @RequestMapping("pMeasureCheck/add_judge")
    @ResponseBody
    public String add_judge(){
        return "";
    }

    @RequestMapping("p_measure_check/add")
    public String add(){
        return "p_measure_check_add";
    }

    @RequestMapping("measure/insert")
    public String insert(FinalMeasuret finalMeasuret){
        return "p_measure_check_list";
    }
}
