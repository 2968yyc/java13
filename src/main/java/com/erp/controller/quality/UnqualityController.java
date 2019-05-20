package com.erp.controller.quality;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Info;
import com.erp.bean.quality.Unqualify;
import com.erp.service.quality.UnqualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("unqualify")
public class UnqualityController {

    @Autowired
    UnqualityService unqualityService;

    /*----------------------------以下是分页查询功能----------------------------*/

    @RequestMapping("find")
    public String find(){
        return "unqualify_list";
    }

    @RequestMapping("list")
    @ResponseBody

    //分页查询
    public QueryVO list(int page, int rows){
        return  unqualityService.selectPageUnqualify(page, rows);
    }


    /*----------------------------以下是添加功能----------------------------*/

    @RequestMapping("add_judge")
    @ResponseBody
    public String add_judge(){
        return "";
    }

    @RequestMapping("add")
    public String add(){
        return "unqualify_add";
    }

    @RequestMapping("insert")
    @ResponseBody
    //插入功能
    public Info insert(Unqualify unqualify){
        boolean b = unqualityService.selectUnqualifyByUnqualifyId(unqualify.getUnqualifyApplyId());
        if (b){
            boolean flag = unqualityService.insertUnqualify(unqualify);
            return returnInfo(flag, "插入失败，请稍后重试！");
        }else {
            return new Info(0,"该不合格品申请编号已经存在，请更换！", null);
        }
    }

    /*----------------------------以下是删除功能----------------------------*/

    @RequestMapping("delete_judge")
    @ResponseBody
    public String delete_judge(){
        return "";
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    //删除功能
    public Info delete_batch(String[] ids){
        boolean flag = unqualityService.deleteUnqualifyById(ids);
        return returnInfo(flag, "删除不合格品信息失败");
    }

    /*----------------------------以下是模糊查询功能----------------------------*/

    @RequestMapping("search_unqualify_by_unqualifyId")
    @ResponseBody
    //根据不合格品申请编号查询的功能
    public QueryVO searchUnqualifyByUnqualifyId(String searchValue, int page, int rows){
        return unqualityService.searchUnqualifyByUnqualifyId(searchValue, page, rows);
    }

    @RequestMapping("search_unqualify_by_productName")
    @ResponseBody
    //根据不合格品申请编号查询的功能
    public QueryVO searchUnqualifyByProductName(String searchValue, int page, int rows){
        return unqualityService.searchUnqualifyByProductName(searchValue, page, rows);
    }


    /*----------------------------以下是编辑功能----------------------------*/

    @RequestMapping("edit_judge")
    @ResponseBody
    public String edit_judge(){
        return "";
    }

    @RequestMapping("edit")
    public String edit(){
        return "unqualify_edit";
    }

    @RequestMapping("update_all")
    @ResponseBody
    public Info update_all(Unqualify unqualify){
        boolean flag = unqualityService.updateUnqualifyByUnqualifyId(unqualify);
        return returnInfo(flag, "修改不合格品申请信息失败");
    }

    public Info returnInfo(boolean flag, String message){
        if (flag){
            return new Info(200,"OK",null);
        }else {
            return new Info(0,message,null);
        }
    }
}
