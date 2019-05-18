package com.erp.controller.material;

import com.erp.bean.QueryVO;
import com.erp.bean.material.Material;
import com.erp.bean.material.Material_receive;
import com.erp.service.material.MaterialReceiveService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public QueryVO<Material_receive> queryReceiveByReceiveId(String searchValue,@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows){

        PageHelper.startPage(page,rows);
        QueryVO<Material_receive> materialReceiveList =  materialReceiveService.queryReceiveByReceiveId(searchValue);

        return materialReceiveList;
    }
    @RequestMapping("search_materialReceive_by_materialId")
    public QueryVO<Material_receive> queryReceiveByMaterialId(String searchValue,@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows){

        PageHelper.startPage(page,rows);
        QueryVO<Material_receive> materialReceiveList =  materialReceiveService.queryReceiveByMaterialId(searchValue);


        return materialReceiveList;
    }

}
