package com.erp.service.impl;

import com.erp.bean.quality.Unqualify;
import com.erp.mapper.quality.UnqualifyMapper;
import com.erp.service.UnqualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UnqualityServiceImpl implements UnqualityService {

    @Autowired
    UnqualifyMapper unqualifyMapper;

    public List<Unqualify> selectAllUnqualify(){
        return unqualifyMapper.selectAllUnqualify();
    }
}
