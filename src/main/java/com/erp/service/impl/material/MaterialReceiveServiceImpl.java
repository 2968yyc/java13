package com.erp.service.impl.material;

import com.erp.bean.QueryVO;
import com.erp.bean.material.Material;
import com.erp.bean.material.Material_receive;
import com.erp.mapper.material.MaterialMapper;
import com.erp.mapper.material.Material_receiveMapper;
import com.erp.service.material.MaterialReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Qiu
 * @Date: 2019/5/17 21:40
 */
@Service
public class MaterialReceiveServiceImpl implements MaterialReceiveService {

    @Autowired
    Material_receiveMapper material_receiveMapper;

    @Autowired
    MaterialMapper materialMapper;

    @Override
    public QueryVO<Material_receive> getMaterialReceiveList() {
        List<Material_receive> list = material_receiveMapper.queryAllMaterialReceive();

        getMaterial(list);

        return new QueryVO<>(list.size(),list);
    }

    @Override
    public QueryVO<Material_receive> queryReceiveByReceiveId(String searchValue) {
        searchValue = "%"+searchValue+"%";
        List<Material_receive> list = material_receiveMapper.queryReceiveByReceiveId(searchValue);
        List<Material_receive> material = getMaterial(list);

        return new QueryVO<Material_receive>(material.size(),material);
    }

    private List<Material_receive> getMaterial(List<Material_receive> list) {
        for (int i = 0; i < list.size(); i++) {
            Material_receive material_consume = list.get(i);
            String materialId = material_consume.getMaterialId();
            Material material = materialMapper.queryMaterialById(materialId);
            material_consume.setMaterial(material);
        }

        return list;
    }

    @Override
    public QueryVO<Material_receive> queryReceiveByMaterialId(String searchValue) {
        searchValue = "%"+searchValue+"%";
        List<Material_receive> MaterialList = material_receiveMapper.queryReceiveByMaterialId(searchValue);
        List<Material_receive> material = getMaterial(MaterialList);
        return new QueryVO<Material_receive>(material.size(),material);
    }

    @Override
    public Material_receive getMaterialReceive(String receiveId) {
        return material_receiveMapper.getMaterialReceiveByReceiveId(receiveId);
    }

    @Override
    public boolean insertMaterialReceive(Material_receive material_receive) {
        int insert = material_receiveMapper.insertMaterialReceive(material_receive);

        return insert == 1?true:false;
    }

}
