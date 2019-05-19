package com.erp.service.quality.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.quality.FinalMeasuret;
import com.erp.bean.quality.ProcessMeasure;
import com.erp.mapper.quality.ProcessMeasureMapper;
import com.erp.service.quality.PMeasureService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author:ZCH
 * @Date:2019/5/19 9:46
 */

@Service
public class PMeasureServiceImpl implements PMeasureService {

    @Autowired
    ProcessMeasureMapper processMeasureMapper;

    @Override
    public QueryVO selectPageProcessMeasure(int page, int rows) {
        int total = processMeasureMapper.countAllProcessMeasure();
        PageHelper.startPage(page, rows);
        List<FinalMeasuret> measurets = processMeasureMapper.selectPMeasureCheckLeft(null);
        return new QueryVO(total, measurets);
    }

    @Override
    public boolean insert(ProcessMeasure processMeasure) {
        int insert = processMeasureMapper.insert(processMeasure);
        return insert == 1;
    }

    @Override
    public boolean updatePMeasureBypMeasureCheckId(ProcessMeasure processMeasure) {
        int update = processMeasureMapper.updateByPrimaryKey(processMeasure);
        return update == 1;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public boolean deletePMeasureBypMeasureCheckIds(String[] ids) {
        for (String id : ids) {
            int key = processMeasureMapper.deleteByPrimaryKey(id);
            if (key != 1){
                return false;
            }
        }
        return true;
    }

    @Override
    public QueryVO searchPMeasureByfpMeasureCheckId(String searchValue, int page, int rows) {
        int total = processMeasureMapper.countAllProcessMeasure();
        PageHelper.startPage(page, rows);
        List<FinalMeasuret> measurets = processMeasureMapper.selectPMeasureCheckLeft(searchValue);
        return new QueryVO(total, measurets);
    }
}