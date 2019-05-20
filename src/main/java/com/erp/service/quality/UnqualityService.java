package com.erp.service.quality;

import com.erp.bean.QueryVO;
import com.erp.bean.quality.Unqualify;

import java.util.List;

public interface UnqualityService {

    List<Unqualify> selectAllUnqualify();
    QueryVO selectPageUnqualify(int page, int rows);
    boolean insertUnqualify(Unqualify unqualify);
    boolean deleteUnqualifyById(String[] ids);
    QueryVO searchUnqualifyByUnqualifyId(String searchValue, int page, int rows);
    boolean updateUnqualifyByUnqualifyId(Unqualify unqualify);
    QueryVO searchUnqualifyByProductName(String searchValue, int page, int rows);
    boolean selectUnqualifyByUnqualifyId(String id);

}
