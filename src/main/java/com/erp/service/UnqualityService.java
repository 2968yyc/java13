package com.erp.service;

import com.erp.bean.QueryVO;
import com.erp.bean.quality.Unqualify;

import java.util.List;

public interface UnqualityService {

    List<Unqualify> selectAllUnqualify();
    QueryVO selectPageUnqualify(int page, int rows);
}
