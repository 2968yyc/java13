package com.erp.service.technology;

import com.erp.bean.technology.Technology_requirement;

import java.util.List;

/**
 * @Author : zjf
 * @Date : 2019/5/17 下午 04:53
 */
public interface Technology_requirementService {
    List<Technology_requirement> findAllByPage(Integer page, Integer rows);

    int deleteByids(String[] ids);

    int addByTechnologyRequirement(Technology_requirement technology_requirement);

    int editBytechnologyRequirement(Technology_requirement technology_requirement);

    int updateById(Technology_requirement technologyRequirement);

    List<Technology_requirement> searchAllByPage(Integer page, Integer rows, String searchValue);

    List<Technology_requirement> searchNameAllByPage(Integer page, Integer rows, String searchValue);

    int judgeById(Technology_requirement technology_requirement);
}
