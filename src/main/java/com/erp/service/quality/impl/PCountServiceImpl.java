package com.erp.service.quality.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.quality.ProcessCount;
import com.erp.mapper.quality.ProcessCountMapper;
import com.erp.service.quality.PCountService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @Author:ZCH
 * @Date:2019/5/19 10:48
 */

@Service
public class PCountServiceImpl implements PCountService {

    @Autowired
    ProcessCountMapper processCountMapper;

    @Override
    public QueryVO selectPageProcessCount(int page, int rows) {
        int total = processCountMapper.countAllProcessMeasure();
        PageHelper.startPage(page, rows);
        List<ProcessCount> processCounts = processCountMapper.selectPCountCheckLeft(null);
        return new QueryVO(total, processCounts);
    }

    @Override
    public boolean insert(ProcessCount processCount) {
        int insert = processCountMapper.insert(processCount);
        return insert == 1;
    }

    @Override
    public boolean updatePCountBypCountCheckId(ProcessCount processCount) {
        int update = processCountMapper.updateByPrimaryKey(processCount);
        return update == 1;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public boolean deletePCountByCountCheckIds(String[] ids) {
        for (String id : ids) {
            int key = processCountMapper.deleteByPrimaryKey(id);
            if (key != 1){
                return false;
            }
        }
        return true;
    }

    @Override
    public QueryVO searchPCountBypCountCheckId(String searchValue, int page, int rows) {
        int total = processCountMapper.countAllProcessCountBySomething(searchValue);
        PageHelper.startPage(page, rows);
        List<ProcessCount> processCounts = processCountMapper.selectPCountCheckLeft(searchValue);
        return new QueryVO(total, processCounts);
    }
}
