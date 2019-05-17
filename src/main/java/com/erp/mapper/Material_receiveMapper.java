package com.erp.mapper;

import com.erp.bean.*;

public interface Material_receiveMapper {
    int updateByPrimaryKeySelective(Material_receive record);

    int updateByPrimaryKey(Material_receive record);
}