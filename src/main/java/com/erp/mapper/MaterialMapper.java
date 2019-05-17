package com.erp.mapper;

import com.erp.bean.material.Material;

public interface MaterialMapper {
    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);
}