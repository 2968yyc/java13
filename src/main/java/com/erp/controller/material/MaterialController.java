package com.erp.controller.material;

import com.erp.annotation.UpdateMethod;
import com.erp.bean.QueryVO;
import com.erp.bean.device.Info;
import com.erp.bean.material.Material;
import com.erp.service.material.MaterialService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Qiu
 * @Date: 2019/5/17 12:10
 */
@Controller
@RequestMapping("material")
public class MaterialController {

    @Autowired
    MaterialService materialService;

    @RequestMapping(value = "list", method = {RequestMethod.GET})
    @ResponseBody
    public QueryVO<Material> materialPageList(@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows) {


        QueryVO<Material> materialList = materialService.getMaterialList(page,rows);

        return materialList;
    }

    @RequestMapping("find")
    public String returnMaterialPage() {
        return "material_list";
    }

    @RequestMapping("search_material_by_materialId")
    @ResponseBody
    public QueryVO<Material> queryMaterialById(String searchValue,@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows){

        PageHelper.startPage(page,rows);

        QueryVO<Material> materialById = materialService.queryMaterialById(searchValue,page,rows);


        return materialById;

    }

    @RequestMapping("/search_material_by_materialType")
    @ResponseBody
    public QueryVO<Material> queryMaterialByType(String searchValue,@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows){

        PageHelper.startPage(page,rows);

        QueryVO<Material> materialById = materialService.queryMaterialByType(searchValue,page,rows);

        return materialById;

    }

    @RequestMapping("get/{id}")
    @ResponseBody
    public Material queryMaterial(@PathVariable("id")String id){

        Material material =  materialService.queryMaterial(id);


        return material;
    }

    @RequestMapping("add_judge")
    public String  materialAdd_judge(){
        return "material_add";
    }

    @RequestMapping("add")
    public String  materialAdd(){
        return "material_add";
    }

    @RequestMapping("insert")
    @ResponseBody
    public Info insertMaterial(@ModelAttribute("material")Material material){

        Info info = new Info();
        String materialId = material.getMaterialId();
        Material queryMaterial = materialService.queryMaterial(materialId);
        if (queryMaterial != null){

            info.setStatus(0);
            info.setMsg("该物料编号已存在");

        }else {

            boolean insert = materialService.insertMaterial(material);
            info = returnMsg(info,insert,"新增成功","新增失败");

        }

        return info;

    }

    @RequestMapping("get_data")
    @ResponseBody
    public List<Material> getMaterial(){

        List<Material> materialList = materialService.queryAllMaterial();

        return materialList;

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

    @UpdateMethod("material")
    @RequestMapping("update_note")
    @ResponseBody
    public Info update_note(Material material){

        Info info = new Info();

        boolean update = materialService.update_note(material);

        info = returnMsg(info, update, "更新成功", "更新失败");


        return info;
    }

    private Info returnMsg(Info info, boolean update, String success, String fail) {
        if (update) {
            info.setStatus(200);
            info.setMsg(success);
        } else {
            info.setStatus(0);
            info.setMsg(fail);
        }
        return info;
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    public Info delete_batch(List<String> ids){

        Info info = new Info();

        boolean isDele = materialService.delete_batch(ids);
        info = returnMsg(info, isDele, "删除成功", "删除失败");

        return info;
    }

    @RequestMapping("edit")
    public String  materialEdit(){
        return "material_edit";
    }

    @UpdateMethod("material")
    @RequestMapping(value = "update_all",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Info update_all(Material material){
        Info info = new Info();

        boolean isUpdate = materialService.update_all(material);

        info = returnMsg(info, isUpdate, "编辑成功", "编辑失败");

        return info;
    }




}

