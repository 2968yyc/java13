package com.erp.service.technology;

import com.erp.bean.technology.Technology_plan;

import java.util.List;

/**
 * @Author : zjf
 * @Date : 2019/5/17 下午 10:32
 */
public interface TechnologyPlanService {

    List<Technology_plan> findAllByPage(Integer page, Integer rows);

    int deleteByids(String[] ids);

    int addByTechnologyPlan(Technology_plan technology_plan);

    int editByTechnology(Technology_plan technology_plan);

    List<Technology_plan> searchAllByPage(Integer page, Integer rows, String searchValue);

    List<Technology_plan> searchNameAllByPage(Integer page, Integer rows, String searchValue);

    Technology_plan queryTechnologyPlan(String id);

    List<Technology_plan> queryAllTechnologyPlan();
}
