package com.erp.mapper.material;

import com.erp.bean.material.Material;

import java.util.List;

public interface MaterialMapper {
    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);

    List<Material> queryAllMaterial();

    Material queryMaterialById(String materialId);

    List<Material> getMaterialById(String searchValue);

    List<Material> getMaterialByType(String searchValue);

    int insertMaterial(Material material);

    int update_note(Material material);

    int delete_batch(String ids);
}