package com.erp.service.material;

import com.erp.bean.QueryVO;
import com.erp.bean.material.Material;

import java.util.List;

/**
 * @Author: Qiu
 * @Date: 2019/5/17 12:11
 */
public interface MaterialService {
    QueryVO<Material> getMaterialList(Integer page, Integer rows);

    QueryVO<Material> queryMaterialById(String searchValue,Integer page, Integer rows);


    QueryVO<Material> queryMaterialByType(String searchValue,Integer page, Integer rows);

    Material queryMaterial(String id);

    boolean insertMaterial(Material material);

    List<Material> queryAllMaterial();

    boolean update_note(Material material);

    boolean delete_batch(List<String> ids);

    boolean update_all(Material material);
}
