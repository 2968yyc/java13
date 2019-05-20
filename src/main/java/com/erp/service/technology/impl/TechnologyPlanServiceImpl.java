package com.erp.service.technology.impl;

import com.erp.bean.technology.Technology;
import com.erp.bean.technology.TechnologyExample;
import com.erp.bean.technology.Technology_plan;
import com.erp.bean.technology.Technology_planExample;
import com.erp.mapper.technology.TechnologyMapper;
import com.erp.mapper.technology.Technology_planMapper;
import com.erp.service.technology.TechnologyPlanService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author : zjf
 * @Date : 2019/5/17 下午 10:32
 */
@Service
public class TechnologyPlanServiceImpl implements TechnologyPlanService {
    @Autowired
    Technology_planMapper technology_planMapper;

    @Autowired
    TechnologyMapper technologyMapper;

    @Override
    public List<Technology_plan> findAllByPage(Integer page, Integer rows) {
        Technology_planExample technology_planExample = new Technology_planExample();
        Technology_planExample.Criteria criteria = technology_planExample.createCriteria();
        criteria.getAllCriteria();
        List<Technology_plan> technology_plans = technology_planMapper.selectByExample(technology_planExample);

        //获取Technology_plan name
        //id lists
        List<String> lists = new ArrayList<>();

        for (Technology_plan technology_plan : technology_plans) {
            lists.add(technology_plan.getTechnologyId());
        }

        //TechnologyExample
        TechnologyExample technologyExample = new TechnologyExample();
        TechnologyExample.Criteria criteria1 = technologyExample.createCriteria();
        criteria1.andTechnologyIdIn(lists);
        //分页查询在最后一次查询之前
        PageHelper.startPage(page, rows);
        List<Technology> technologies = technologyMapper.selectByExample(technologyExample);

        for (Technology technology : technologies) {
            for (Technology_plan technology_plan : technology_plans) {
                if(technology.getTechnologyId().equals(technology_plan.getTechnologyId())){
                    //technology_plan获得name
                    technology_plan.setTechnologyName(technology.getTechnologyName());
                }
            }
        }
        return technology_plans;
    }

    @Override
    public int deleteByids(String[] ids) {
        Technology_planExample technology_planExample = new Technology_planExample();
        Technology_planExample.Criteria criteria = technology_planExample.createCriteria();
        List<String> lists = Arrays.asList(ids);
        criteria.andTechnologyPlanIdIn(lists);
        int i = technology_planMapper.deleteByExample(technology_planExample);
        if(i < 1){
            return 0;
        }
        return 1;
    }

    @Override
    public int addByTechnologyPlan(Technology_plan technology_plan) {
        int insert = technology_planMapper.insert(technology_plan);
        return insert;
    }

    @Override
    public int editByTechnology(Technology_plan technology_plan) {
        int update = technology_planMapper.updateByPrimaryKey(technology_plan);
        return update;
    }

    @Override
    public List<Technology_plan> searchAllByPage(Integer page, Integer rows, String searchValue) {
        Technology_planExample technology_planExample = new Technology_planExample();
        Technology_planExample.Criteria criteria = technology_planExample.createCriteria();
        criteria.andTechnologyPlanIdLike("%" + searchValue + "%");
        List<Technology_plan> technology_plans = technology_planMapper.selectByExample(technology_planExample);

        //id lists
        List<String> lists = new ArrayList<>();

        for (Technology_plan technology_plan : technology_plans) {
            lists.add(technology_plan.getTechnologyId());
        }

        //TechnologyExample
        TechnologyExample technologyExample = new TechnologyExample();
        TechnologyExample.Criteria criteria1 = technologyExample.createCriteria();
        criteria1.andTechnologyIdIn(lists);
        //分页查询在最后一次查询之前
        PageHelper.startPage(page, rows);
        List<Technology> technologies = technologyMapper.selectByExample(technologyExample);

        for (Technology technology : technologies) {
            for (Technology_plan technology_plan : technology_plans) {
                if(technology.getTechnologyId().equals(technology_plan.getTechnologyId())){
                    //technology_plan获得name
                    technology_plan.setTechnologyName(technology.getTechnologyName());
                }
            }
        }
        return technology_plans;
    }

    @Override
    public List<Technology_plan> searchNameAllByPage(Integer page, Integer rows, String searchValue) {
        //TechnologyExample
        TechnologyExample technologyExample = new TechnologyExample();
        TechnologyExample.Criteria criteria1 = technologyExample.createCriteria();
        criteria1.andTechnologyNameLike("%" + searchValue + "%");
        List<Technology> technologies = technologyMapper.selectByExample(technologyExample);

        //Technology name and id list
//        List<Technology_plan> technology_plans = new ArrayList<>();
        List<String> ids = new ArrayList<>();

        Map<String,String> map = new HashMap<>();
        for (Technology technology : technologies) {
            map.put(technology.getTechnologyId(),technology.getTechnologyName());
            ids.add(technology.getTechnologyId());
        }

        //Technology_planExample
        Technology_planExample technology_planExample = new Technology_planExample();
        Technology_planExample.Criteria criteria = technology_planExample.createCriteria();
        criteria.andTechnologyIdIn(ids);
        List<Technology_plan> technology_plans = technology_planMapper.selectByExample(technology_planExample);
        for (Technology_plan technology_plan : technology_plans) {
            for (String s : map.keySet()) {
                if(s.equals(technology_plan.getTechnologyId())){
                    technology_plan.setTechnologyName(map.get(s));
                }
            }
        }
        return technology_plans;
    }

    @Override
    public Technology_plan queryTechnologyPlan(String id) {
        Technology_plan technology_plan = technology_planMapper.selectByPrimaryKey(id);
        return technology_plan;
    }

    @Override
    public List<Technology_plan> queryAllTechnologyPlan() {
        Technology_planExample technology_planExample = new Technology_planExample();
        Technology_planExample.Criteria criteria = technology_planExample.createCriteria();
        criteria.getAllCriteria();
        List<Technology_plan> technology_plans = technology_planMapper.selectByExample(technology_planExample);
        return technology_plans;
    }

    @Override
    public int judgeById(Technology_plan technology_plan) {
        Technology_plan technology_plan1 = technology_planMapper.selectByPrimaryKey(technology_plan.getTechnologyPlanId());
        if(technology_plan1 == null ){
            return 0;
        }
        return 1;
    }
}
