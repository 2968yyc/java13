package com.erp.mapper.technology;

import com.erp.bean.technology.technology_requirement;
import com.erp.bean.technology.technology_requirementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface technology_requirementMapper {
    long countByExample(technology_requirementExample example);

    int deleteByExample(technology_requirementExample example);

    int deleteByPrimaryKey(String technologyRequirementId);

    int insert(technology_requirement record);

    int insertSelective(technology_requirement record);

    List<technology_requirement> selectByExample(technology_requirementExample example);

    technology_requirement selectByPrimaryKey(String technologyRequirementId);

    int updateByExampleSelective(@Param("record") technology_requirement record, @Param("example") technology_requirementExample example);

    int updateByExample(@Param("record") technology_requirement record, @Param("example") technology_requirementExample example);

    int updateByPrimaryKeySelective(technology_requirement record);

    int updateByPrimaryKey(technology_requirement record);
}