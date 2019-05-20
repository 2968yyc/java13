package com.erp.controller.quality;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Info;
import com.erp.bean.quality.ProcessMeasure;
import com.erp.service.quality.PMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:ZCH
 * @Date:2019/5/19 9:45
 */

@Controller
public class PMeasureController {

    @Autowired
    PMeasureService pMeasureService;

    /*----------------------------以下是分页查询功能----------------------------*/

    @RequestMapping("p_measure_check/find")
    public String find(){
        return "p_measure_check_list";
    }

    @RequestMapping("p_measure_check/list")
    @ResponseBody
    public QueryVO list(int page, int rows){
        return pMeasureService.selectPageProcessMeasure(page, rows);
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

    @RequestMapping("p_measure_check/insert")
    @ResponseBody
    public Info insert(ProcessMeasure processMeasure){
        boolean b = pMeasureService.selectPMeasureBypMeasureCheckId(processMeasure.getpMeasureCheckId());
        if (b){
            boolean flag = pMeasureService.insert(processMeasure);
            return returnInfo(flag, "插入失败，请稍后重试！");
        }else {
            return new Info(0, "工序计量质检编号已经存在，请更换！",null);
        }
    }

    /*----------------------------以下是修改功能----------------------------*/

    @RequestMapping("pMeasureCheck/edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "";
    }

    @RequestMapping("p_measure_check/edit")
    public String edit(){
        return "p_measure_check_edit";
    }

    @RequestMapping("p_measure_check/update_all")
    @ResponseBody
    public Info update_all(ProcessMeasure processMeasure){
        boolean flag = pMeasureService.updatePMeasureBypMeasureCheckId(processMeasure);
        return returnInfo(flag, "更新失败，请稍后重试！");
    }

    /*----------------------------以下是删除功能----------------------------*/

    @RequestMapping("pMeasureCheck/delete_judge")
    @ResponseBody
    public String delete_judge(){
        return "";
    }

    @RequestMapping("p_measure_check/delete_batch")
    @ResponseBody
    //删除功能
    public Info delete_batch(String[] ids){
        boolean flag = pMeasureService.deletePMeasureBypMeasureCheckIds(ids);
        return returnInfo(flag, "删除失败，请稍后重试！");
    }

    /*----------------------------以下是模糊查询功能----------------------------*/

    @RequestMapping("p_measure_check/search_pMeasureCheck_by_pMeasureCheckId")
    @ResponseBody
    public QueryVO searchFMeasureCheckByFMeasureCheckId(String searchValue, int page, int rows){
        return pMeasureService.searchPMeasureByfpMeasureCheckId(searchValue, page, rows);
    }

    public Info returnInfo(boolean flag, String message){
        if (flag){
            return new Info(200,"OK",null);
        }else {
            return new Info(0,message,null);
        }
    }
}
