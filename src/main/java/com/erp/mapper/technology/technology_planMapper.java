package com.erp.mapper.technology;

import com.erp.bean.technology.technology_plan;
import com.erp.bean.technology.technology_planExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface technology_planMapper {
    long countByExample(technology_planExample example);

    int deleteByExample(technology_planExample example);

    int deleteByPrimaryKey(String technologyPlanId);

    int insert(technology_plan record);

    int insertSelective(technology_plan record);

    List<technology_plan> selectByExample(technology_planExample example);

    technology_plan selectByPrimaryKey(String technologyPlanId);

    int updateByExampleSelective(@Param("record") technology_plan record, @Param("example") technology_planExample example);

    int updateByExample(@Param("record") technology_plan record, @Param("example") technology_planExample example);

    int updateByPrimaryKeySelective(technology_plan record);

    int updateByPrimaryKey(technology_plan record);
}