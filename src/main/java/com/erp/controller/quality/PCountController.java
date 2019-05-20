package com.erp.controller.quality;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Info;
import com.erp.bean.quality.ProcessCount;
import com.erp.service.quality.PCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:ZCH
 * @Date:2019/5/19 10:47
 */

@Controller
public class PCountController {

    @Autowired
    PCountService pCountService;

    /*----------------------------以下是分页查询功能----------------------------*/

    @RequestMapping("p_count_check/find")
    public String find(){
        return "p_count_check_list";
    }

    @RequestMapping("p_count_check/list")
    @ResponseBody
    public QueryVO list(int page, int rows){
        return pCountService.selectPageProcessCount(page, rows);
    }

    /*----------------------------以下是添加功能----------------------------*/

    @RequestMapping("pCountCheck/add_judge")
    @ResponseBody
    public String add_judge(){
        return "";
    }

    @RequestMapping("p_count_check/add")
    public String add(){
        return "p_count_check_add";
    }

    @RequestMapping("p_count_check/insert")
    @ResponseBody
    public Info insert(ProcessCount processCount){
        boolean b = pCountService.selectPCountBypCountCheckId(processCount.getpCountCheckId());
        if (b){
            boolean flag = pCountService.insert(processCount);
            return returnInfo(flag, "插入失败，请稍后重试！");
        }else {
            return new Info(0, "工序计数质检编号已经存在，请更换！", null);
        }
    }

    /*----------------------------以下是修改功能----------------------------*/

    @RequestMapping("pCountCheck/edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "";
    }

    @RequestMapping("p_count_check/edit")
    public String edit(){
        return "p_count_check_edit";
    }

    @RequestMapping("p_count_check/update_all")
    @ResponseBody
    public Info update_all(ProcessCount processCount){
        boolean flag = pCountService.updatePCountBypCountCheckId(processCount);
        return returnInfo(flag, "更新失败，请稍后重试！");
    }

    /*----------------------------以下是删除功能----------------------------*/

    @RequestMapping("pCountCheck/delete_judge")
    @ResponseBody
    public String delete_judge(){
        return "";
    }

    @RequestMapping("p_count_check/delete_batch")
    @ResponseBody
    //删除功能
    public Info delete_batch(String[] ids){
        boolean flag = pCountService.deletePCountByCountCheckIds(ids);
        return returnInfo(flag, "删除失败，请稍后重试！");
    }

    /*----------------------------以下是模糊查询功能----------------------------*/

    @RequestMapping("p_count_check/search_pCountCheck_by_pCountCheckId")
    @ResponseBody
    public QueryVO searchPCountBypCountCheckId(String searchValue, int page, int rows){
        return pCountService.searchPCountBypCountCheckId(searchValue, page, rows);
    }

    public Info returnInfo(boolean flag, String message){
        if (flag){
            return new Info(200,"OK",null);
        }else {
            return new Info(0,message,null);
        }
    }
}
