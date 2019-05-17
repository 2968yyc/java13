package com.erp.controller.material;

import com.erp.bean.QueryVO;
import com.erp.bean.material.Material;
import com.erp.service.material.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

        QueryVO<Material> materialById = materialService.queryMaterialById(searchValue);

        return materialById;

    }

    @RequestMapping("/search_material_by_materialType")
    @ResponseBody
    public QueryVO<Material> queryMaterialByType(String searchValue,@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows){

        QueryVO<Material> materialById = materialService.queryMaterialByType(searchValue);

        return materialById;

    }
}

