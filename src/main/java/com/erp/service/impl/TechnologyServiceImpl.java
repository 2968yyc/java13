package com.erp.service.impl;

import com.erp.bean.technology.Technology;
import com.erp.bean.technology.TechnologyExample;

import com.erp.mapper.technology.TechnologyMapper;
import com.erp.service.TechnologyService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : zjf
 * @Date : 2019/5/17 下午 01:27
 */
@Service
public class TechnologyServiceImpl implements TechnologyService {
    @Autowired
    TechnologyMapper technologyMapper;

    @Override
    public List<Technology> findAllByPage(Integer page, Integer rows) {
        TechnologyExample technologyExample = new TechnologyExample();
        TechnologyExample.Criteria criteria = technologyExample.createCriteria();
        criteria.getAllCriteria();
        PageHelper.startPage(page, rows);
        List<Technology> technologies = technologyMapper.selectByExample(technologyExample);
        return technologies;
    }
}
