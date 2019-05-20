package com.erp.controller.quality;

import com.erp.annotation.UpdateMethod;
import com.erp.bean.QueryVO;
import com.erp.bean.device.Info;
import com.erp.bean.quality.FinalCount;
import com.erp.service.quality.FCountService;
import com.erp.utils.PermissionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    public Map<String, String> add_judge(HttpServletRequest request){
        return PermissionUtils.permissionCheck("fCountCheck:add",request);
    }

    @RequestMapping("f_count_check/add")
    public String add(){
        return "f_count_check_add";
    }

    @RequestMapping("f_count_check/insert")
    @ResponseBody
    public Info insert(FinalCount finalCount){
        boolean b = fCountService.selectFCountByfCountCheckId(finalCount.getfCountCheckId());
        if (b){
            boolean flag = fCountService.insert(finalCount);
            return returnInfo(flag, "插入失败，请稍后重试！");
        }else {
            return new Info(0, "成品计数质检编号已经存在，请更换！", null);
        }
    }

    /*----------------------------以下是修改功能----------------------------*/

    @RequestMapping("fCountCheck/edit_judge")
    @ResponseBody
    public Map<String, String> edit_judge(HttpServletRequest request){
        return PermissionUtils.permissionCheck("fCountCheck:edit",request);
    }

    @RequestMapping("f_count_check/edit")
    public String edit(){
        return "f_count_check_edit";
    }

    @UpdateMethod("fCountCheck")
    @RequestMapping("f_count_check/update_all")
    @ResponseBody
    public Info update_all(FinalCount finalCount){
        boolean flag = fCountService.updateFCountByfCountCheckId(finalCount);
        return returnInfo(flag, "更新失败，请稍后重试！");
    }

    /*----------------------------以下是删除功能----------------------------*/

    @RequestMapping("fCountCheck/delete_judge")
    @ResponseBody
    public Map<String, String> delete_judge(HttpServletRequest request){
        return PermissionUtils.permissionCheck("fCountCheck:delete",request);
    }

    @RequestMapping("f_count_check/delete_batch")
    @ResponseBody
    //删除功能
    public Info delete_batch(String[] ids){
        boolean flag = fCountService.deleteFCountByfCountCheckIds(ids);
        return returnInfo(flag, "删除失败，请稍后重试！");
    }

    /*----------------------------以下是模糊查询功能----------------------------*/

    @RequestMapping("f_count_check/search_fCountCheck_by_fCountCheckId")
    @ResponseBody
    public QueryVO searchFMeasureCheckByFMeasureCheckId(String searchValue, int page, int rows){
        String fMeasureCheckId = "%" + searchValue + "%";
        return fCountService.searchFCountByfCountCheckId(fMeasureCheckId, page, rows);
    }

    @RequestMapping("f_count_check/search_fCountCheck_by_orderId")
    @ResponseBody
    public QueryVO searchfMeasureCheckByOrderId(String searchValue, int page, int rows){
        String orderId = "%" + searchValue + "%";
        return fCountService.searchFCountByOrderId(orderId, page, rows);
    }

    public Info returnInfo(boolean flag, String message){
        if (flag){
            return new Info(200,"OK",null);
        }else {
            return new Info(0,message,null);
        }
    }
}
