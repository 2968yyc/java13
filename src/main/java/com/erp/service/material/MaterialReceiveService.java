package com.erp.service.material;

import com.erp.bean.QueryVO;
import com.erp.bean.material.Material_receive;

/**
 * @Author: Qiu
 * @Date: 2019/5/17 21:40
 */
public interface MaterialReceiveService {
    QueryVO<Material_receive> getMaterialReceiveList();

    QueryVO<Material_receive> queryReceiveByReceiveId(String searchValue);

    QueryVO<Material_receive> queryReceiveByMaterialId(String searchValue);
}
