package com.erp.service.quality;

import com.erp.bean.QueryVO;

/**
 * @Author:ZCH
 * @Date:2019/5/18 10:47
 */
public interface MeasureService {
    QueryVO selectPageMeasure(int page, int rows);
}
