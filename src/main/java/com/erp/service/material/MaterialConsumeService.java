package com.erp.service.material;

import com.erp.bean.QueryVO;
import com.erp.bean.material.Material_consume;

/**
 * @Author: Qiu
 * @Date: 2019/5/17 21:39
 */
public interface MaterialConsumeService {

    QueryVO<Material_consume> getMaterialConsumeList();

    QueryVO<Material_consume> queryConsumeByConsumeId(String searchValue);

    QueryVO<Material_consume> queryConsumeByWorkId(String searchValue);

    QueryVO<Material_consume> queryConsumeByMaterialId(String searchValue);
}
