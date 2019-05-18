package com.erp.mapper.material;

import com.erp.bean.QueryVO;
import com.erp.bean.material.Material_consume;

import java.util.List;

public interface Material_consumeMapper {
    int updateByPrimaryKeySelective(Material_consume record);

    int updateByPrimaryKey(Material_consume record);

    List<Material_consume> queryAllMaterialConsume();

    List<Material_consume> queryConsumeByConsumeId(String searchValue);

    List<Material_consume> queryConsumeByWorkId(String searchValue);

    List<Material_consume> queryConsumeByMaterialId(String searchValue);
}