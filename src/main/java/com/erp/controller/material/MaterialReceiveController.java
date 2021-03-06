package com.erp.controller.material;

import com.erp.annotation.UpdateMethod;
import com.erp.bean.QueryVO;
import com.erp.bean.device.Info;
import com.erp.bean.material.Material;

import com.erp.bean.material.Material_receive;
import com.erp.service.material.MaterialReceiveService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author: Qiu
 * @Date: 2019/5/17 21:37
 */
@Controller
@RequestMapping("materialReceive")
public class MaterialReceiveController {

    @Autowired
    MaterialReceiveService materialReceiveService;


    @RequestMapping("find")
    public String returnMaterialReceivePage(){
        return "materialReceive_list";
    }

    @RequestMapping(value = "list", method = {RequestMethod.GET})
    @ResponseBody
    public QueryVO<Material_receive> materialReceivePageList(@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows) {


        QueryVO<Material_receive> materialReceiveList = materialReceiveService.getMaterialReceiveList(page,rows);

        return materialReceiveList;
    }

    @RequestMapping("search_materialReceive_by_receiveId")
    @ResponseBody
    public QueryVO<Material_receive> queryReceiveByReceiveId(String searchValue,@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows){


        QueryVO<Material_receive> materialReceiveList =  materialReceiveService.queryReceiveByReceiveId(searchValue,page,rows);

        return materialReceiveList;
    }

    @RequestMapping("search_materialReceive_by_materialId")
    @ResponseBody
    public QueryVO<Material_receive> queryReceiveByMaterialId(String searchValue,@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows){


        QueryVO<Material_receive> materialReceiveList =  materialReceiveService.queryReceiveByMaterialId(searchValue,page,rows);


        return materialReceiveList;
    }

    @RequestMapping("add_judge")
    public String  materialReceiveAdd_judge(){
        return "materialReceive_add";
    }

    @RequestMapping("add")
    public String  materialReceiveAdd(){
        return "materialReceive_add";
    }

    @RequestMapping("insert")
    @ResponseBody
    public Info insertMaterialConsume(@ModelAttribute("materialReceive")Material_receive material_receive){

        Info info = new Info();


        Material_receive queryMaterial_receive = materialReceiveService.getMaterialReceive(material_receive.getReceiveId());

        if (queryMaterial_receive!=null){
            info.setStatus(0);
            info.setMsg("该收入编号已存在");
        }else {
            boolean insert = materialReceiveService.insertMaterialReceive(material_receive);
            info = returnMsg(info,insert,"新增成功","新增失败");
        }

        return info;
    }

    @RequestMapping("delete_judge")
    @ResponseBody
    public String  deleteDudge(){
        return "";
    }

    @RequestMapping("edit_judge")
    @ResponseBody
    public  String  editDudge(){
        return "";
    }

    @UpdateMethod("materialReceive")
    @RequestMapping("update_note")
    @ResponseBody
    public Info update_note(Material_receive material_receive){

        Info info = new Info();

        boolean update = materialReceiveService.update_note(material_receive);

        info = returnMsg(info, update, "更新成功", "更新失败");


        return info;
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    public Info delete_batch(List<String> ids){

        Info info = new Info();

        boolean isDele = materialReceiveService.delete_batch(ids);
        info =returnMsg(info, isDele, "删除成功", "删除失败");

        return info;
    }

    @RequestMapping("edit")
    public String  materialEdit(){
        return "materialReceive_edit";
    }


    @UpdateMethod("materialReceive")
    @RequestMapping("update_all")
    @ResponseBody
    public Info update_all(Material_receive material_receive){
        Info info = new Info();

        boolean isUpdate = materialReceiveService.update_all(material_receive);

        info = returnMsg(info, isUpdate, "编辑成功", "编辑失败");

        return info;
    }

    private Info returnMsg(Info info, boolean isUpdate, String success, String fail) {
        if (isUpdate) {
            info.setStatus(200);
            info.setMsg(success);
        } else {
            info.setStatus(0);
            info.setMsg(fail);
        }
        return info;
    }


}
