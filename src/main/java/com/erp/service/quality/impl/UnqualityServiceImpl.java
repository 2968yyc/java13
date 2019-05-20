package com.erp.service.quality.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.quality.Unqualify;
import com.erp.mapper.quality.UnqualifyMapper;
import com.erp.service.quality.UnqualityService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
        List<Unqualify> unqualifies = unqualifyMapper.selectAllPageUnqualifyLeft();
        return new QueryVO(total, unqualifies);
    }

    @Override
    public boolean insertUnqualify(Unqualify unqualify){
        try {
            int insert = unqualifyMapper.insert(unqualify);
            return insert == 1;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public boolean deleteUnqualifyById(String[] ids){
        try {
            for (String id : ids) {
                int key = unqualifyMapper.deleteByPrimaryKey(id);
                if (key != 1){
                    return false;
                }
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public QueryVO searchUnqualifyByUnqualifyId(String searchValue, int page, int rows){
        int total = unqualifyMapper.countAllUnqualifyBySomething(searchValue, null);
        PageHelper.startPage(page, rows);
        List<Unqualify> unqualifies = unqualifyMapper.selectAllPageUnqualifyLeftByUnqualifyId(searchValue);
        return new QueryVO(total, unqualifies);
    }

    @Override
    public boolean updateUnqualifyByUnqualifyId(Unqualify unqualify){
        try {
            int update = unqualifyMapper.updateByPrimaryKey(unqualify);
            return update == 1;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public QueryVO searchUnqualifyByProductName(String searchValue, int page, int rows) {
        int total = unqualifyMapper.countAllUnqualifyBySomething(null, searchValue);
        PageHelper.startPage(page, rows);
        List<Unqualify> unqualifies = unqualifyMapper.selectAllPageUnqualifyLeftByProductName(searchValue);
        return new QueryVO(total, unqualifies);
    }

    @Override
    public boolean selectUnqualifyByUnqualifyId(String id){
        Unqualify unqualify = unqualifyMapper.selectByPrimaryKey(id);
        return unqualify == null;
    }
}
