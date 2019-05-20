package com.erp.service.quality;

import com.erp.bean.QueryVO;
import com.erp.bean.quality.FinalCount;

/**
 * @Author:ZCH
 * @Date:2019/5/19 8:14
 */
public interface FCountService {
    QueryVO selectPageFCount(int page, int rows);

    boolean insert(FinalCount finalCount);

    boolean updateFCountByfCountCheckId(FinalCount finalCount);

    boolean deleteFCountByfCountCheckIds(String[] ids);

    QueryVO searchFCountByfCountCheckId(String searchValue, int page, int rows);

    QueryVO searchFCountByOrderId(String searchValue, int page, int rows);

    boolean selectFCountByfCountCheckId(String fCountCheckId);
}
