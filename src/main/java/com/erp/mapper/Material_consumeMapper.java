package com.erp.mapper;

import com.erp.bean.*;

public interface Material_consumeMapper {
    int updateByPrimaryKeySelective(Material_consume record);

    int updateByPrimaryKey(Material_consume record);
}