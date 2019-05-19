package com.erp.service.technology;

import com.erp.bean.technology.Technology;

import java.util.List;

/**
 * @Author : zjf
 * @Date : 2019/5/17 下午 01:26
 */
public interface TechnologyService {
    List<Technology> findAllByPage(Integer page, Integer rows);

    int deleteByids(String[] ids);

    int addByTechnology(Technology technology);

    int editByTechnology(Technology technology);

    List<Technology> searchAllByPage(Integer page, Integer rows, String searchValue);

    List<Technology> searchNameAllByPage(Integer page, Integer rows, String searchValue);

    Technology queryTechnology(String id);

    List<Technology> queryAllTechnology();

}
