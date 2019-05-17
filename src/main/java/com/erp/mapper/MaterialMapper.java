package com.erp.mapper;

import com.erp.bean.*;

public interface MaterialMapper {
    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);
}