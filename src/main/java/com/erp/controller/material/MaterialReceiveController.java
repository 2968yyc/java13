package com.erp.controller.material;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Info;
import com.erp.bean.material.Material;

import com.erp.bean.material.Material_receive;
import com.erp.service.material.MaterialReceiveService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;



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

        PageHelper.startPage(page,rows);


        QueryVO<Material_receive> materialReceiveList = materialReceiveService.getMaterialReceiveList();

        return materialReceiveList;
    }

    @RequestMapping("search_materialReceive_by_receiveId")
    @ResponseBody
    public QueryVO<Material_receive> queryReceiveByReceiveId(String searchValue,@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows){

        PageHelper.startPage(page,rows);
        QueryVO<Material_receive> materialReceiveList =  materialReceiveService.queryReceiveByReceiveId(searchValue);

        return materialReceiveList;
    }

    @RequestMapping("search_materialReceive_by_materialId")
    @ResponseBody
    public QueryVO<Material_receive> queryReceiveByMaterialId(String searchValue,@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows){

        PageHelper.startPage(page,rows);
        QueryVO<Material_receive> materialReceiveList =  materialReceiveService.queryReceiveByMaterialId(searchValue);


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
