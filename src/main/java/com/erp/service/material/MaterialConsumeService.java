package com.erp.service.material;

import com.erp.bean.QueryVO;
import com.erp.bean.material.Material_consume;

import java.util.List;

/**
 * @Author: Qiu
 * @Date: 2019/5/17 21:39
 */
public interface MaterialConsumeService {

    QueryVO<Material_consume> getMaterialConsumeList(Integer page, Integer rows);

    QueryVO<Material_consume> queryConsumeByConsumeId(String searchValue,Integer page, Integer rows);

    QueryVO<Material_consume> queryConsumeByWorkId(String searchValue,Integer page, Integer rows);

    QueryVO<Material_consume> queryConsumeByMaterialId(String searchValue,Integer page, Integer rows);

    Material_consume getConsumeByConsumeId(String consumeId);

    boolean insertMaterialConsume(Material_consume material_consume);

    boolean update_note(Material_consume material_consume);

    boolean delete_batch(List<String> ids);

    boolean update_all(Material_consume material_consume);
}
