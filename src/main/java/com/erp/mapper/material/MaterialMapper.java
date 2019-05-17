package com.erp.mapper.material;

import com.erp.bean.material.Material;

public interface MaterialMapper {
    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);
}