package com.erp.service.impl;

import com.erp.bean.technology.Technology_plan;
import com.erp.bean.technology.Technology_planExample;
import com.erp.mapper.technology.Technology_planMapper;
import com.erp.service.TechnologyPlanService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : zjf
 * @Date : 2019/5/17 下午 10:32
 */
@Service
public class TechnologyPlanServiceImpl implements TechnologyPlanService {
    @Autowired
    Technology_planMapper technology_planMapper;
    @Override
    public List<Technology_plan> findAllByPage(Integer page, Integer rows) {
        Technology_planExample technology_planExample = new Technology_planExample();
        Technology_planExample.Criteria criteria = technology_planExample.createCriteria();
        criteria.getAllCriteria();
        PageHelper.startPage(page, rows);
        List<Technology_plan> technology_plans = technology_planMapper.selectByExample(technology_planExample);
        return technology_plans;
    }
}
