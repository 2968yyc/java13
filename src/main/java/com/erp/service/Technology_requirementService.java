package com.erp.service;

import com.erp.bean.technology.Technology_requirement;

import java.util.List;

/**
 * @Author : zjf
 * @Date : 2019/5/17 下午 04:53
 */
public interface Technology_requirementService {
    List<Technology_requirement> findAllByPage(Integer page, Integer pageSize);
}
