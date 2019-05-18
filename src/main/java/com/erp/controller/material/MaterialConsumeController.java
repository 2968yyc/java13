package com.erp.controller.material;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Info;
import com.erp.bean.material.Material;
import com.erp.bean.material.Material_consume;
import com.erp.service.material.MaterialConsumeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Qiu
 * @Date: 2019/5/17 21:35
 */

@Controller
@RequestMapping("materialConsume")
public class MaterialConsumeController {

    @Autowired
    MaterialConsumeService materialConsumeService;

    @RequestMapping("find")
    public String returnMaterialConsumePage() {
        return "materialConsume_list";
    }

    @RequestMapping(value = "list", method = {RequestMethod.GET})
    @ResponseBody
    public QueryVO<Material_consume> materialConsumePageList(@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows) {
        PageHelper.startPage(page,rows);


        QueryVO<Material_consume> materialConsumeList = materialConsumeService.getMaterialConsumeList();

        return materialConsumeList;
    }


    @RequestMapping("search_materialConsume_by_consumeId")
    @ResponseBody
    public QueryVO<Material_consume> queryReceiveByConsumeId(String searchValue,@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows){

        PageHelper.startPage(page,rows);
        QueryVO<Material_consume> materialReceiveList =  materialConsumeService.queryConsumeByConsumeId(searchValue);


        return materialReceiveList;
    }
    @RequestMapping("search_materialConsume_by_workId")
    @ResponseBody
    public QueryVO<Material_consume> queryReceiveByWorkId(String searchValue,@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows){

        PageHelper.startPage(page,rows);
        QueryVO<Material_consume> materialReceiveList =  materialConsumeService.queryConsumeByWorkId(searchValue);


        return materialReceiveList;
    }
    @RequestMapping("search_materialConsume_by_materialId")
    @ResponseBody
    public QueryVO<Material_consume> queryReceiveByMaterialId(String searchValue,@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows){

        PageHelper.startPage(page,rows);
        QueryVO<Material_consume> materialReceiveList =  materialConsumeService.queryConsumeByMaterialId(searchValue);


        return materialReceiveList;
    }

    @RequestMapping("add_judge")
    public String  materialConsumeAdd_judge(){
        return "materialConsume_add";
    }

    @RequestMapping("add")
    public String  materialConsumeAdd(){
        return "materialConsume_add";
    }

    @RequestMapping("insert")
    @ResponseBody
    public Info insertMaterialConsume(@ModelAttribute("materialConsume")Material_consume material_consume){

        Info info = new Info();



        return info;
    }

}
