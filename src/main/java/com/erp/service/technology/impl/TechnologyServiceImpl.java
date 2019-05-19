package com.erp.service.technology.impl;

import com.erp.bean.technology.Technology;
import com.erp.bean.technology.TechnologyExample;

import com.erp.mapper.technology.TechnologyMapper;
import com.erp.service.technology.TechnologyService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @Override
    public int deleteByids(String[] ids) {
        TechnologyExample technologyExample = new TechnologyExample();
        TechnologyExample.Criteria criteria = technologyExample.createCriteria();
        List<String> lists = Arrays.asList(ids);
        criteria.andTechnologyIdIn(lists);
        int i = technologyMapper.deleteByExample(technologyExample);
        if(i < 1){
            return 0;
        }
        return 1;
    }

    @Override
    public int addByTechnology(Technology technology) {
        int insert = technologyMapper.insert(technology);
        return insert;
    }

    @Override
    public int editByTechnology(Technology technology) {
        int update = technologyMapper.updateByPrimaryKey(technology);
        return update;
    }

    @Override
    public List<Technology> searchAllByPage(Integer page, Integer rows, String searchValue) {
        TechnologyExample technologyExample = new TechnologyExample();
        TechnologyExample.Criteria criteria = technologyExample.createCriteria();
        criteria.andTechnologyIdLike("%" + searchValue + "%");
        PageHelper.startPage(page, rows);
        List<Technology> technologies = technologyMapper.selectByExample(technologyExample);
        return technologies;
    }

    @Override
    public List<Technology> searchNameAllByPage(Integer page, Integer rows, String searchValue) {
        TechnologyExample technologyExample = new TechnologyExample();
        TechnologyExample.Criteria criteria = technologyExample.createCriteria();
        criteria.andTechnologyNameLike("%" + searchValue + "%");
        PageHelper.startPage(page, rows);
        List<Technology> technologies = technologyMapper.selectByExample(technologyExample);
        return technologies;
    }

    @Override
    public Technology queryTechnology(String id) {
        Technology technology = technologyMapper.selectByPrimaryKey(id);
        return technology;
    }

    @Override
    public List<Technology> queryAllTechnology() {
        TechnologyExample technologyExample = new TechnologyExample();
        TechnologyExample.Criteria criteria = technologyExample.createCriteria();
        criteria.getAllCriteria();
        List<Technology> technologies = technologyMapper.selectByExample(technologyExample);
        return technologies;
    }
}
