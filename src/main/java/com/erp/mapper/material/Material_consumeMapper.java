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

    Material_consume getConsumeByConsumeId(String consumeId);

    int insertMaterialConsume(Material_consume material_consume);

    int update_note(Material_consume material_consume);

    int delete_batch(String ids);

    Material_consume getMaterial_consume(String consumeId);
}