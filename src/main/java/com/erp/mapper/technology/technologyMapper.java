package com.erp.mapper.technology;

import com.erp.bean.technology.technology;
import com.erp.bean.technology.technologyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface technologyMapper {
    long countByExample(technologyExample example);

    int deleteByExample(technologyExample example);

    int deleteByPrimaryKey(String technologyId);

    int insert(technology record);

    int insertSelective(technology record);

    List<technology> selectByExample(technologyExample example);

    technology selectByPrimaryKey(String technologyId);

    int updateByExampleSelective(@Param("record") technology record, @Param("example") technologyExample example);

    int updateByExample(@Param("record") technology record, @Param("example") technologyExample example);

    int updateByPrimaryKeySelective(technology record);

    int updateByPrimaryKey(technology record);
}