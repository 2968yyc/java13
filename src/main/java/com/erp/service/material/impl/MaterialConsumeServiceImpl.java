package com.erp.service.material.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.material.Material;
import com.erp.bean.material.Material_consume;
import com.erp.mapper.material.MaterialMapper;
import com.erp.mapper.material.Material_consumeMapper;
import com.erp.service.material.MaterialConsumeService;

import com.github.pagehelper.PageHelper;
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
    public QueryVO<Material_consume> getMaterialConsumeList(Integer page, Integer rows) {


        List<Material_consume> list1 = material_consumeMapper.queryAllMaterialConsume();


        PageHelper.startPage(page,rows);

        List<Material_consume> list = material_consumeMapper.queryAllMaterialConsume();


        List<Material_consume> material = getMaterial(list);

        return new QueryVO<>(list1.size(),material);
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
    public QueryVO<Material_consume> queryConsumeByConsumeId(String searchValue,Integer page, Integer rows) {

        searchValue = "%"+searchValue+"%";
        List<Material_consume> listByConsumeId = material_consumeMapper.queryConsumeByConsumeId(searchValue);
        PageHelper.startPage(page,rows);
        List<Material_consume> listByConsumeId1 = material_consumeMapper.queryConsumeByConsumeId(searchValue);
        List<Material_consume> material = getMaterial(listByConsumeId1);
        return new QueryVO<>(listByConsumeId.size(),material);

    }

    @Override
    public QueryVO<Material_consume> queryConsumeByWorkId(String searchValue,Integer page, Integer rows) {
        searchValue = "%"+searchValue+"%";
        List<Material_consume> list = material_consumeMapper.queryConsumeByWorkId(searchValue);

        PageHelper.startPage(page,rows);

        List<Material_consume> list1 = material_consumeMapper.queryConsumeByWorkId(searchValue);
        List<Material_consume> material = getMaterial(list1);
        return new QueryVO<>(list.size(),material);
    }

    @Override
    public QueryVO<Material_consume> queryConsumeByMaterialId(String searchValue,Integer page, Integer rows) {
        searchValue = "%"+searchValue+"%";
        List<Material_consume> list = material_consumeMapper.queryConsumeByMaterialId(searchValue);
        PageHelper.startPage(page,rows);

        List<Material_consume> list1 = material_consumeMapper.queryConsumeByMaterialId(searchValue);
        List<Material_consume> material = getMaterial(list1);
        return new QueryVO<>(list.size(),material);
    }

    @Override
    public Material_consume getConsumeByConsumeId(String consumeId) {
        return material_consumeMapper.getConsumeByConsumeId(consumeId);
    }

    @Override
    public boolean insertMaterialConsume(Material_consume material_consume) {
        int insert = material_consumeMapper.insertMaterialConsume(material_consume);

        return insert ==1?true:false;
    }

    @Override
    public boolean update_note(Material_consume material_consume) {
        int update = material_consumeMapper.update_note(material_consume);

        return update==1?true:false;
    }

    @Override
    public boolean delete_batch(List<String> ids) {
        int isDele = 0;


        for (int i = 0; i < ids.size(); i++) {
            isDele = material_consumeMapper.delete_batch(ids.get(i));
        }
        return isDele==1?true:false;
    }

    @Override
    public boolean update_all(Material_consume material_consume) {

        int update = material_consumeMapper.updateByPrimaryKeySelective(material_consume);


        return update ==1?true:false;

    }


}
