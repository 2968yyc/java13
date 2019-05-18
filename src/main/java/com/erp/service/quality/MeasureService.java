package com.erp.service.quality;

import com.erp.bean.QueryVO;
import com.erp.bean.quality.FinalMeasuret;

/**
 * @Author:ZCH
 * @Date:2019/5/18 10:47
 */
public interface MeasureService {
    QueryVO selectPageMeasure(int page, int rows);

    boolean updateMeasureByfMeasureCheckId(FinalMeasuret finalMeasuret);

    boolean deleteMeasureByfMeasureCheckIds(String[] ids);

    QueryVO searchFMeasureCheckByFMeasureCheckId(String searchValue, int page, int rows);

    QueryVO searchfMeasureCheckByOrderId(String searchValue, int page, int rows);

    boolean insert(FinalMeasuret finalMeasuret);
}
