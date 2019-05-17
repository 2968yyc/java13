package com.erp.mapper.technology;

import com.erp.bean.technology.process;
import com.erp.bean.technology.processExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface processMapper {
    long countByExample(processExample example);

    int deleteByExample(processExample example);

    int deleteByPrimaryKey(String processId);

    int insert(process record);

    int insertSelective(process record);

    List<process> selectByExample(processExample example);

    process selectByPrimaryKey(String processId);

    int updateByExampleSelective(@Param("record") process record, @Param("example") processExample example);

    int updateByExample(@Param("record") process record, @Param("example") processExample example);

    int updateByPrimaryKeySelective(process record);

    int updateByPrimaryKey(process record);
}