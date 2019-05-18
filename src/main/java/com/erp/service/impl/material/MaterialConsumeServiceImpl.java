package com.erp.service.impl.material;

import com.erp.bean.QueryVO;
import com.erp.bean.material.Material;
import com.erp.bean.material.Material_consume;
import com.erp.mapper.material.MaterialMapper;
import com.erp.mapper.material.Material_consumeMapper;
import com.erp.service.material.MaterialConsumeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @Author: Qiu
 * @Date: 2019/5/17 21:40
 */
@Service
public class MaterialConsumeServiceImpl implements MaterialConsumeService {

    @Autowired
    Material_consumeMapper material_consumeMapper;

    @Autowired
    MaterialMapper materialMapper;

    @Override
    public QueryVO<Material_consume> getMaterialConsumeList() {

        List<Material_consume> list = material_consumeMapper.queryAllMaterialConsume();

        List<Material_consume> material = getMaterial(list);

        return new QueryVO<>(material.size(),material);
    }

    private List<Material_consume> getMaterial(List<Material_consume> list) {
        for (int i = 0; i < list.size(); i++) {
            Material_consume material_consume = list.get(i);
            String materialId = material_consume.getMaterialId();
            Material material = materialMapper.queryMaterialById(materialId);
            material_consume.setMaterial(material);

        }

        return list;

    }

    @Override
    public QueryVO<Material_consume> queryConsumeByConsumeId(String searchValue) {
        searchValue = "%"+searchValue+"%";
        List<Material_consume> listByConsumeId = material_consumeMapper.queryConsumeByConsumeId(searchValue);
        List<Material_consume> material = getMaterial(listByConsumeId);
        return new QueryVO<>(material.size(),material);

    }

    @Override
    public QueryVO<Material_consume> queryConsumeByWorkId(String searchValue) {
        searchValue = "%"+searchValue+"%";
        List<Material_consume> list = material_consumeMapper.queryConsumeByWorkId(searchValue);
        List<Material_consume> material = getMaterial(list);
        return new QueryVO<>(material.size(),material);
    }

    @Override
    public QueryVO<Material_consume> queryConsumeByMaterialId(String searchValue) {
        searchValue = "%"+searchValue+"%";
        List<Material_consume> list = material_consumeMapper.queryConsumeByMaterialId(searchValue);
        List<Material_consume> material = getMaterial(list);
        return new QueryVO<>(material.size(),material);
    }


}
