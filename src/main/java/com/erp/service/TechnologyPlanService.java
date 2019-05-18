package com.erp.service;

import com.erp.bean.technology.Technology_plan;

import java.util.List;

/**
 * @Author : zjf
 * @Date : 2019/5/17 下午 10:32
 */
public interface TechnologyPlanService {

    List<Technology_plan> findAllByPage(Integer page, Integer rows);
}
