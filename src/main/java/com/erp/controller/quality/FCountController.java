package com.erp.controller.quality;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Info;
import com.erp.bean.quality.FinalCount;
import com.erp.service.quality.FCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:ZCH
 * @Date:2019/5/19 8:06
 */

@Controller
public class FCountController {

    @Autowired
    FCountService fCountService;

    /*----------------------------以下是分页查询功能----------------------------*/

    @RequestMapping("f_count_check/find")
    public String find(){
        return "f_count_check_list";
    }

    @RequestMapping("f_count_check/list")
    @ResponseBody
    public QueryVO list(int page, int rows){
        return fCountService.selectPageFCount(page, rows);
    }

    /*----------------------------以下是添加功能----------------------------*/

    @RequestMapping("fCountCheck/add_judge")
    @ResponseBody
    public String add_judge(){
        return "";
    }

    @RequestMapping("f_count_check/add")
    public String add(){
        return "measurement_add";
    }

    @RequestMapping("f_count_check/insert")
    @ResponseBody
    public Info insert(FinalCount finalCount){
        boolean flag = fCountService.insert(finalCount);
        if (flag){
            return new Info(200,"OK",null);
        }else {
            return new Info(0,"error",null);
        }
    }

    /*----------------------------以下是修改功能----------------------------*/

    @RequestMapping("fCountCheck/edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "";
    }

    @RequestMapping("f_count_check/edit")
    public String edit(){
        return "measurement_edit";
    }

    @RequestMapping("f_count_check/update_all")
    @ResponseBody
    public Info update_all(FinalCount finalCount){
        boolean flag = fCountService.updateFCountByfCountCheckId(finalCount);
        if (flag){
            return new Info(200,"OK",null);
        }else {
            return new Info(0,"error",null);
        }
    }

    /*----------------------------以下是删除功能----------------------------*/

    @RequestMapping("fCountCheck/delete_judge")
    @ResponseBody
    public String delete_judge(){
        return "";
    }

    @RequestMapping("f_count_check/delete_batch")
    @ResponseBody
    //删除功能
    public Info delete_batch(String[] ids){
        boolean flag = fCountService.deleteFCountByfCountCheckIds(ids);
        if (flag){
            return new Info(200,"OK",null);
        }else {
            return new Info(0,"error",null);
        }
    }

    /*----------------------------以下是模糊查询功能----------------------------*/

    @RequestMapping("f_count_check/search_fCountCheck_by_fCountCheckId")
    @ResponseBody
    public QueryVO searchFMeasureCheckByFMeasureCheckId(String searchValue, int page, int rows){
        return fCountService.searchFCountByfCountCheckId(searchValue, page, rows);
    }

    @RequestMapping("f_count_check/search_fCountCheck_by_orderId")
    @ResponseBody
    public QueryVO searchfMeasureCheckByOrderId(String searchValue, int page, int rows){
        return fCountService.searchFCountByOrderId(searchValue, page, rows);
    }
}
