package com.erp.mapper.material;

import com.erp.bean.material.Material_receive;

import java.util.List;

public interface Material_receiveMapper {
    int updateByPrimaryKeySelective(Material_receive record);

    int updateByPrimaryKey(Material_receive record);

    List<Material_receive> queryAllMaterialReceive();

    List<Material_receive> queryReceiveByReceiveId(String searchValue);

    List<Material_receive> queryReceiveByMaterialId(String searchValue);

    Material_receive getMaterialReceiveByReceiveId(String receiveId);

    int insertMaterialReceive(Material_receive material_receive);

    int update_note(Material_receive material_receive);

    int delete_batch(String ids);
}