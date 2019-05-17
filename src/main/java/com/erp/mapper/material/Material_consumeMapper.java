package com.erp.mapper.material;

import com.erp.bean.material.Material_consume;

import java.util.List;

public interface Material_consumeMapper {
    int updateByPrimaryKeySelective(Material_consume record);

    int updateByPrimaryKey(Material_consume record);

    List<Material_consume> queryAllMaterialConsume();
}