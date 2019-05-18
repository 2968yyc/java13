package com.erp.service.material;

import com.erp.bean.QueryVO;
import com.erp.bean.material.Material_receive;

/**
 * @Author: Qiu
 * @Date: 2019/5/17 21:40
 */
public interface MaterialReceiveService {
    QueryVO<Material_receive> getMaterialReceiveList(Integer page, Integer rows);

    QueryVO<Material_receive> queryReceiveByReceiveId(String searchValue,Integer page, Integer rows);

    QueryVO<Material_receive> queryReceiveByMaterialId(String searchValue,Integer page, Integer rows);

    Material_receive getMaterialReceive(String materialId);

    boolean insertMaterialReceive(Material_receive material_receive);

    boolean update_note(Material_receive material_receive);

    boolean delete_batch(String ids);

    boolean update_all(Material_receive material_receive);
}
