package com.erp.service.quality.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.quality.FinalMeasuret;
import com.erp.bean.quality.FinalMeasuretExample;
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
        FinalMeasuretExample finalMeasuretExample = new FinalMeasuretExample();
        List<FinalMeasuret> measurets = finalMeasuretMapper.selectByExample(finalMeasuretExample);
        return new QueryVO(total, measurets);
    }
}
