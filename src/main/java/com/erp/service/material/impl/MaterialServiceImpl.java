package com.erp.service.material.impl;

import com.erp.bean.QueryVO;

import com.erp.bean.material.Material;
import com.erp.mapper.material.MaterialMapper;
import com.erp.service.material.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Qiu
 * @Date: 2019/5/17 12:11
 */

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    MaterialMapper materialMapper;

    @Override
    public QueryVO<Material> getMaterialList() {

        List<Material> list = materialMapper.queryAllMaterial();



        return new QueryVO<Material>(list.size(),list);
    }

    @Override
    public QueryVO<Material> queryMaterialById(String searchValue) {

        searchValue = "%"+searchValue+"%";


        List<Material> materialList= materialMapper.getMaterialById(searchValue);

        return new QueryVO<Material>(materialList.size(),materialList);
    }

    @Override
    public QueryVO<Material> queryMaterialByType(String searchValue) {
        searchValue = "%"+searchValue+"%";

        List<Material> materialList = materialMapper.getMaterialByType(searchValue);

        return new QueryVO<Material>(materialList.size(),materialList);
    }

    @Override
    public Material queryMaterial(String id) {
        Material material = materialMapper.queryMaterialById(id);
        return material;
    }

    @Override
    public boolean insertMaterial(Material material) {


        int insert = materialMapper.insertMaterial(material);

        return insert == 1?true:false;

    }

    @Override
    public List<Material> queryAllMaterial() {
        List<Material> materialList = materialMapper.queryAllMaterial();
        return materialList;
    }
}
