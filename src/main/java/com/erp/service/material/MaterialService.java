package com.erp.service.material;

import com.erp.bean.QueryVO;
import com.erp.bean.material.Material;

/**
 * @Author: Qiu
 * @Date: 2019/5/17 12:11
 */
public interface MaterialService {
    QueryVO<Material> getMaterialList();

    QueryVO<Material> queryMaterialById(String searchValue);


    QueryVO<Material> queryMaterialByType(String searchValue);

    Material queryMaterial(String id);

    boolean insertMaterial(Material material);
}
