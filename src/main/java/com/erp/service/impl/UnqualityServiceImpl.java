package com.erp.service.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.quality.Unqualify;
import com.erp.bean.quality.UnqualifyExample;
import com.erp.mapper.quality.UnqualifyMapper;
import com.erp.service.UnqualityService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UnqualityServiceImpl implements UnqualityService {

    @Autowired
    UnqualifyMapper unqualifyMapper;

    @Override
    public List<Unqualify> selectAllUnqualify(){
        return unqualifyMapper.selectAllUnqualify();
    }

    @Override
    public QueryVO selectPageUnqualify(int page, int rows){
        int total = unqualifyMapper.countAllUnqualify();
        PageHelper.startPage(page, rows);
        UnqualifyExample unqualifyExample = new UnqualifyExample();
        unqualifyExample.or();
        List<Unqualify> unqualifies = unqualifyMapper.selectByExample(unqualifyExample);
        return new QueryVO(total, unqualifies);
    }
}
