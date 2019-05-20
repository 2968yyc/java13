package com.erp.service.material.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.material.Material;
import com.erp.bean.material.Material_receive;
import com.erp.mapper.material.MaterialMapper;
import com.erp.mapper.material.Material_receiveMapper;
import com.erp.service.material.MaterialReceiveService;
import com.github.pagehelper.PageHelper;
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
    public QueryVO<Material_receive> getMaterialReceiveList(Integer page, Integer rows) {
        List<Material_receive> list = material_receiveMapper.queryAllMaterialReceive();

        PageHelper.startPage(page,rows);

        List<Material_receive> list1 = material_receiveMapper.queryAllMaterialReceive();

        getMaterial(list1);

        return new QueryVO<>(list.size(),list1);
    }

    @Override
    public QueryVO<Material_receive> queryReceiveByReceiveId(String searchValue,Integer page, Integer rows) {
        searchValue = "%"+searchValue+"%";
        List<Material_receive> list = material_receiveMapper.queryReceiveByReceiveId(searchValue);

        PageHelper.startPage(page,rows);
        List<Material_receive> list1 = material_receiveMapper.queryReceiveByReceiveId(searchValue);

        List<Material_receive> material = getMaterial(list1);

        return new QueryVO<>(list.size(),material);
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
    public QueryVO<Material_receive> queryReceiveByMaterialId(String searchValue,Integer page, Integer rows) {
        searchValue = "%"+searchValue+"%";
        List<Material_receive> materialList = material_receiveMapper.queryReceiveByMaterialId(searchValue);

        PageHelper.startPage(page,rows);
        List<Material_receive> materialList1 = material_receiveMapper.queryReceiveByMaterialId(searchValue);
        List<Material_receive> material = getMaterial(materialList1);
        return new QueryVO<>(materialList.size(),material);
    }

    @Override
    public Material_receive getMaterialReceive(String receiveId) {
        return material_receiveMapper.getMaterialReceiveByReceiveId(receiveId);
    }

    @Override
    public boolean insertMaterialReceive(Material_receive material_receive) {

        Material material = materialMapper.queryMaterialById(material_receive.getMaterialId());

        material.setRemaining(material.getRemaining()+material_receive.getAmount());

        materialMapper.updateByPrimaryKeySelective(material);


        int insert = material_receiveMapper.insertMaterialReceive(material_receive);

        return insert == 1?true:false;
    }

    @Override
    public boolean update_note(Material_receive material_receive) {
        int update = material_receiveMapper.update_note(material_receive);
        return update==1?true:false;
    }


    @Override
    public boolean delete_batch(List<String> ids) {
        int isDele = 0;


        for (int i = 0; i < ids.size(); i++) {
            isDele = material_receiveMapper.delete_batch(ids.get(i));
        }
        return isDele==1?true:false;
    }

    @Override
    public boolean update_all(Material_receive material_receive) {


        Material_receive materialReceive = material_receiveMapper.getMaterialReceiveByReceiveId(material_receive.getReceiveId());

        int i = materialReceive.getAmount() - material_receive.getAmount();

        Material material = materialMapper.queryMaterialById(material_receive.getMaterialId());

        material.setRemaining(material.getRemaining()+i);

        materialMapper.updateByPrimaryKeySelective(material);


        int update = material_receiveMapper.updateByPrimaryKeySelective(material_receive);


        return update ==1?true:false;
    }

}
