package com.erp.service;

import com.erp.bean.technology.Technology;

import java.util.List;

/**
 * @Author : zjf
 * @Date : 2019/5/17 下午 01:26
 */
public interface TechnologyService {
    List<Technology> findAllByPage(Integer page, Integer rows);
}
