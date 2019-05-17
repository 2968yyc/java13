package com.erp.mapper.technology;

import com.erp.bean.technology.Technology_plan;
import com.erp.bean.technology.Technology_planExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Technology_planMapper {
    long countByExample(Technology_planExample example);

    int deleteByExample(Technology_planExample example);

    int deleteByPrimaryKey(String technologyPlanId);

    int insert(Technology_plan record);

    int insertSelective(Technology_plan record);

    List<Technology_plan> selectByExample(Technology_planExample example);

    Technology_plan selectByPrimaryKey(String technologyPlanId);

    int updateByExampleSelective(@Param("record") Technology_plan record, @Param("example") Technology_planExample example);

    int updateByExample(@Param("record") Technology_plan record, @Param("example") Technology_planExample example);

    int updateByPrimaryKeySelective(Technology_plan record);

    int updateByPrimaryKey(Technology_plan record);
}