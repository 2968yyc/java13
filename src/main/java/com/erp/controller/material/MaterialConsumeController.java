package com.erp.controller.material;

import com.erp.bean.QueryVO;
import com.erp.bean.material.Material;
import com.erp.bean.material.Material_consume;
import com.erp.service.material.MaterialConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

        QueryVO<Material_consume> materialConsumeList = materialConsumeService.getMaterialConsumeList();

        return materialConsumeList;
    }

}
