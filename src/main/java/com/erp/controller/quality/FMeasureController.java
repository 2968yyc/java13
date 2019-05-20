package com.erp.controller.quality;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Info;
import com.erp.bean.quality.FinalMeasuret;
import com.erp.service.quality.FMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:ZCH
 * @Date:2019/5/18 8:57
 */

@Controller
public class FMeasureController {

    @Autowired
    FMeasureService fMeasureService;

    /*----------------------------以下是分页查询功能----------------------------*/

    @RequestMapping("measure/find")
    public String find(){
        return "measurement_list";
    }

    @RequestMapping("measure/list")
    @ResponseBody
    public QueryVO list(int page, int rows){
        return fMeasureService.selectPageMeasure(page, rows);
    }

    /*----------------------------以下是添加功能----------------------------*/

    @RequestMapping("fMeasureCheck/add_judge")
    @ResponseBody
    public String add_judge(){
        return "";
    }

    @RequestMapping("measure/add")
    public String add(){
        return "measurement_add";
    }

    @RequestMapping("measure/insert")
    @ResponseBody
    public Info insert(FinalMeasuret finalMeasuret){
        boolean b = fMeasureService.selectfMeasureCheckByFMeasureCheckId(finalMeasuret.getfMeasureCheckId());
        if (b){
            boolean flag = fMeasureService.insert(finalMeasuret);
            return returnInfo(flag, "插入失败，请稍后重试！");
        }else {
            return new Info(0,"该成品质量质检编号已经存在，请更换！", null);
        }
    }

    /*----------------------------以下是修改功能----------------------------*/

    @RequestMapping("fMeasureCheck/edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "";
    }

    @RequestMapping("measure/edit")
    public String edit(){
        return "measurement_edit";
    }

    @RequestMapping("fMeasureCheck/update_all")
    @ResponseBody
    public Info update_all(FinalMeasuret finalMeasuret){
        boolean flag = fMeasureService.updateMeasureByfMeasureCheckId(finalMeasuret);
        return returnInfo(flag, "更新失败，请稍后重试！");
    }

    /*----------------------------以下是删除功能----------------------------*/

    @RequestMapping("fMeasureCheck/delete_judge")
    @ResponseBody
    public String delete_judge(){
        return "";
    }

    @RequestMapping("measure/delete_batch")
    @ResponseBody
    //删除功能
    public Info delete_batch(String[] ids){
        boolean flag = fMeasureService.deleteMeasureByfMeasureCheckIds(ids);
        return returnInfo(flag, "删除失败，请稍后重试！");
    }

    /*----------------------------以下是模糊查询功能----------------------------*/

    @RequestMapping("measure/search_fMeasureCheck_by_fMeasureCheckId")
    @ResponseBody
    public QueryVO searchFMeasureCheckByFMeasureCheckId(String searchValue, int page, int rows){
        return fMeasureService.searchFMeasureCheckByFMeasureCheckId(searchValue, page, rows);
    }

    @RequestMapping("measure/search_fMeasureCheck_by_orderId")
    @ResponseBody
    public QueryVO searchfMeasureCheckByOrderId(String searchValue, int page, int rows){
        return fMeasureService.searchfMeasureCheckByOrderId(searchValue, page, rows);
    }

    public Info returnInfo(boolean flag, String message){
        if (flag){
            return new Info(200,"OK",null);
        }else {
            return new Info(0,message,null);
        }
    }
}
