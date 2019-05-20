package com.erp.service.technology.impl;

import com.erp.bean.technology.*;
import com.erp.mapper.technology.TechnologyMapper;
import com.erp.mapper.technology.Technology_requirementMapper;
import com.erp.service.technology.Technology_requirementService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author : zjf
 * @Date : 2019/5/17 下午 04:54
 */
@Service
public class Technology_requirementServiceImpl implements Technology_requirementService {
    @Autowired
    Technology_requirementMapper technology_requirementMapper;

    @Autowired
    TechnologyMapper technologyMapper;

    @Override
    public List<Technology_requirement> findAllByPage(Integer page, Integer rows) {
        Technology_requirementExample technology_requirementExample = new Technology_requirementExample();
        Technology_requirementExample.Criteria criteria = technology_requirementExample.createCriteria();
        criteria.getAllCriteria();
        List<Technology_requirement> technology_requirements = technology_requirementMapper.selectByExample(technology_requirementExample);

        //id lists
        List<String> lists = new ArrayList<>();

        for (Technology_requirement technology_requirement : technology_requirements) {
            lists.add(technology_requirement.getTechnologyId());
        }

        //TechnologyExample
        TechnologyExample technologyExample = new TechnologyExample();
        TechnologyExample.Criteria criteria1 = technologyExample.createCriteria();
        criteria1.andTechnologyIdIn(lists);
        //分页查询在最后一次查询之前
        PageHelper.startPage(page, rows);
        List<Technology> technologies = technologyMapper.selectByExample(technologyExample);

        for (Technology technology : technologies) {
            for (Technology_requirement technology_requirement : technology_requirements) {
                if(technology.getTechnologyId().equals(technology_requirement.getTechnologyId())){
                    technology_requirement.setTechnologyName(technology.getTechnologyName());
                }
            }
        }
        return technology_requirements;
    }

    @Override
    public int deleteByids(String[] ids) {
        Technology_requirementExample technology_requirementExample = new Technology_requirementExample();
        Technology_requirementExample.Criteria criteria = technology_requirementExample.createCriteria();
        List<String> lists = Arrays.asList(ids);
        criteria.andTechnologyRequirementIdIn(lists);
        int i = technology_requirementMapper.deleteByExample(technology_requirementExample);
        if(i < 1){
            return 0;
        }
        return 1;
    }

    @Override
    public int addByTechnologyRequirement(Technology_requirement technology_requirement) {
        int insert = technology_requirementMapper.insert(technology_requirement);
        return insert;
    }

    @Override
    public int editBytechnologyRequirement(Technology_requirement technology_requirement) {
        int update = technology_requirementMapper.updateByPrimaryKey(technology_requirement);
        return update;
    }

    @Override
    public int updateById(Technology_requirement technologyRequirement) {
        int update = technology_requirementMapper.updateByPrimaryKey(technologyRequirement);
        return update;
    }

    @Override
    public List<Technology_requirement> searchAllByPage(Integer page, Integer rows, String searchValue) {
        Technology_requirementExample technology_requirementExample = new Technology_requirementExample();
        Technology_requirementExample.Criteria criteria = technology_requirementExample.createCriteria();
        criteria.andTechnologyRequirementIdLike("%" + searchValue + "%");
        List<Technology_requirement> technology_requirements = technology_requirementMapper.selectByExample(technology_requirementExample);

        //id lists
        List<String> lists = new ArrayList<>();

        for (Technology_requirement technology_requirement : technology_requirements) {
            lists.add(technology_requirement.getTechnologyId());
        }

        //TechnologyExample
        TechnologyExample technologyExample = new TechnologyExample();
        TechnologyExample.Criteria criteria1 = technologyExample.createCriteria();
        criteria1.andTechnologyIdIn(lists);
        //分页查询在最后一次查询之前
        PageHelper.startPage(page, rows);
        List<Technology> technologies = technologyMapper.selectByExample(technologyExample);

        for (Technology technology : technologies) {
            for (Technology_requirement technology_requirement : technology_requirements) {
                if(technology.getTechnologyId().equals(technology_requirement.getTechnologyId())){
                    //technology_plan获得name
                    technology_requirement.setTechnologyName(technology.getTechnologyName());
                }
            }
        }
        return technology_requirements;
    }

    @Override
    public List<Technology_requirement> searchNameAllByPage(Integer page, Integer rows, String searchValue) {
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
        Technology_requirementExample technology_requirementExample = new Technology_requirementExample();
        Technology_requirementExample.Criteria criteria = technology_requirementExample.createCriteria();
        criteria.andTechnologyIdIn(ids);
        List<Technology_requirement> technology_requirements = technology_requirementMapper.selectByExample(technology_requirementExample);
        for (Technology_requirement technology_requirement : technology_requirements) {
            for (String s : map.keySet()) {
                if(s.equals(technology_requirement.getTechnologyId())){
                    technology_requirement.setTechnologyName(map.get(s));
                }
            }
        }
        return technology_requirements;
    }

    @Override
    public int judgeById(Technology_requirement technology_requirement) {
        Technology_requirement technology_requirement1 = technology_requirementMapper.selectByPrimaryKey(technology_requirement.getTechnologyRequirementId());
        if(technology_requirement1 == null){
            return 0;
        }
        return 1;
    }
}
