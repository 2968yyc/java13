package com.erp.controller.material;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Info;
import com.erp.bean.material.Material;
import com.erp.service.material.MaterialService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        PageHelper.startPage(page,rows);
        QueryVO<Material> materialList = materialService.getMaterialList();

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

        QueryVO<Material> materialById = materialService.queryMaterialById(searchValue);

        List<Material> getrows = materialById.getrows();

        System.out.printf(getrows.toString());

        return materialById;

    }

    @RequestMapping("/search_material_by_materialType")
    @ResponseBody
    public QueryVO<Material> queryMaterialByType(String searchValue,@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows){

        PageHelper.startPage(page,rows);

        QueryVO<Material> materialById = materialService.queryMaterialByType(searchValue);

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
            if (!insert){
                info.setStatus(0);
                info.setMsg("新增失败");
            }else {
                info.setMsg("新增成功");
                info.setStatus(200);
            }

        }

        return info;

    }



}

