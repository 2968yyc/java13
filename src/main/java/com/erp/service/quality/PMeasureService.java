package com.erp.service.quality;

import com.erp.bean.QueryVO;
import com.erp.bean.quality.ProcessMeasure;

/**
 * @Author:ZCH
 * @Date:2019/5/19 9:46
 */
public interface PMeasureService {
    QueryVO selectPageProcessMeasure(int page, int rows);

    boolean insert(ProcessMeasure processMeasure);

    boolean updatePMeasureBypMeasureCheckId(ProcessMeasure processMeasure);

    boolean deletePMeasureBypMeasureCheckIds(String[] ids);

    QueryVO searchPMeasureByfpMeasureCheckId(String searchValue, int page, int rows);
}
