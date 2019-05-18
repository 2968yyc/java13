package com.erp.service.quality.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.quality.FinalMeasuret;
import com.erp.bean.quality.FinalMeasuretExample;
import com.erp.bean.quality.Unqualify;
import com.erp.mapper.quality.FinalMeasuretMapper;
import com.erp.service.quality.MeasureService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author:ZCH
 * @Date:2019/5/18 10:49
 */
@Service
public class MeasureServiceImpl implements MeasureService {

    @Autowired
    FinalMeasuretMapper finalMeasuretMapper;

    @Override
    public QueryVO selectPageMeasure(int page, int rows) {
        int total = finalMeasuretMapper.countAllFinalMeasure();
        PageHelper.startPage(page, rows);
        List<FinalMeasuret> measurets = finalMeasuretMapper.selectAllFMeasureCheckLeft();
        return new QueryVO(total, measurets);
    }

    @Override
    public boolean updateMeasureByfMeasureCheckId(FinalMeasuret finalMeasuret) {
        int update = finalMeasuretMapper.updateByPrimaryKey(finalMeasuret);
        return update == 1;
    }

    @Override
    public boolean deleteMeasureByfMeasureCheckIds(String[] ids) {
        for (String id : ids) {
            int key = finalMeasuretMapper.deleteByPrimaryKey(id);
            if (key != 1){
                return false;
            }
        }
        return true;
    }

    @Override
    public QueryVO searchFMeasureCheckByFMeasureCheckId(String searchValue, int page, int rows) {
        int total = finalMeasuretMapper.countAllFinalMeasure();
        PageHelper.startPage(page, rows);
        List<FinalMeasuret> measurets = finalMeasuretMapper.searchFMeasureCheckByFMeasureCheckIdLeft(searchValue);
        return new QueryVO(total, measurets);
    }

    @Override
    public QueryVO searchfMeasureCheckByOrderId(String searchValue, int page, int rows) {
        int total = finalMeasuretMapper.countAllFinalMeasure();
        PageHelper.startPage(page, rows);
        List<FinalMeasuret> measurets = finalMeasuretMapper.searchfMeasureCheckByOrderIdLeft(searchValue);
        return new QueryVO(total, measurets);
    }

    @Override
    public boolean insert(FinalMeasuret finalMeasuret) {
        int insert = finalMeasuretMapper.insert(finalMeasuret);
        return insert == 1;
    }


}
