package com.erp.service.impl;

import com.erp.bean.technology.Technology_requirement;
import com.erp.bean.technology.Technology_requirementExample;
import com.erp.mapper.technology.Technology_requirementMapper;
import com.erp.service.Technology_requirementService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : zjf
 * @Date : 2019/5/17 下午 04:54
 */
@Service
public class Technology_requirementServiceImpl implements Technology_requirementService {
    @Autowired
    Technology_requirementMapper technology_requirementMapper;
    @Override
    public List<Technology_requirement> findAllByPage(Integer page, Integer pageSize) {
        Technology_requirementExample technology_requirementExample = new Technology_requirementExample();
        Technology_requirementExample.Criteria criteria = technology_requirementExample.createCriteria();
        criteria.getAllCriteria();
        PageHelper.startPage(page, pageSize);
        List<Technology_requirement> technology_requirements = technology_requirementMapper.selectByExample(technology_requirementExample);
        return technology_requirements;
    }
}
