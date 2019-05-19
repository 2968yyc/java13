package com.erp.service.quality;

import com.erp.bean.QueryVO;
import com.erp.bean.quality.ProcessCount;

/**
 * @Author:ZCH
 * @Date:2019/5/19 10:48
 */
public interface PCountService {
    QueryVO selectPageProcessCount(int page, int rows);

    boolean insert(ProcessCount processCount);

    boolean updatePCountBypCountCheckId(ProcessCount processCount);

    boolean deletePCountByCountCheckIds(String[] ids);

    QueryVO searchPCountBypCountCheckId(String searchValue, int page, int rows);
}
