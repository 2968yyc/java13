package com.erp.service.quality.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.quality.FinalCount;
import com.erp.bean.quality.FinalMeasuret;
import com.erp.mapper.quality.FinalCountMapper;
import com.erp.service.quality.FCountService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author:ZCH
 * @Date:2019/5/19 8:14
 */

@Service
public class FCountServiceImpl implements FCountService {

    @Autowired
    FinalCountMapper finalCountMapper;

    @Override
    public QueryVO selectPageFCount(int page, int rows) {
        int total = finalCountMapper.countAllFinalCount();
        PageHelper.startPage(page, rows);
        List<FinalMeasuret> measurets = finalCountMapper.selectFinalCountLeft(null, null);
        return new QueryVO(total, measurets);
    }

    @Override
    public boolean insert(FinalCount finalCount) {
        try{
            int insert = finalCountMapper.insert(finalCount);
            return insert == 1;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateFCountByfCountCheckId(FinalCount finalCount) {
        try {
            int update = finalCountMapper.updateByPrimaryKey(finalCount);
            return update == 1;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public boolean deleteFCountByfCountCheckIds(String[] ids) {
        try {
            for (String id : ids) {
                int key = finalCountMapper.deleteByPrimaryKey(id);
                if (key != 1){
                    return false;
                }
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public QueryVO searchFCountByfCountCheckId(String searchValue, int page, int rows) {
        int total = finalCountMapper.countAllFinalCountBySomething(searchValue, null);
        PageHelper.startPage(page, rows);
        List<FinalMeasuret> measurets = finalCountMapper.selectFinalCountLeft(searchValue, null);
        return new QueryVO(total, measurets);
    }

    @Override
    public QueryVO searchFCountByOrderId(String searchValue, int page, int rows) {
        int total = finalCountMapper.countAllFinalCountBySomething(null, searchValue);
        PageHelper.startPage(page, rows);
        List<FinalMeasuret> measurets = finalCountMapper.selectFinalCountLeft(null, searchValue);
        return new QueryVO(total, measurets);
    }

    @Override
    public boolean selectFCountByfCountCheckId(String fCountCheckId){
        FinalCount finalCount = finalCountMapper.selectByPrimaryKey(fCountCheckId);
        return finalCount == null;
    }
}
