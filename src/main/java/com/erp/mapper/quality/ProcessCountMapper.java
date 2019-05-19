package com.erp.mapper.quality;

import com.erp.bean.quality.ProcessCount;
import com.erp.bean.quality.ProcessCountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcessCountMapper {
    long countByExample(ProcessCountExample example);

    int deleteByExample(ProcessCountExample example);

    int deleteByPrimaryKey(String pCountCheckId);

    int insert(ProcessCount record);

    int insertSelective(ProcessCount record);

    List<ProcessCount> selectByExample(ProcessCountExample example);

    ProcessCount selectByPrimaryKey(String pCountCheckId);

    int updateByExampleSelective(@Param("record") ProcessCount record, @Param("example") ProcessCountExample example);

    int updateByExample(@Param("record") ProcessCount record, @Param("example") ProcessCountExample example);

    int updateByPrimaryKeySelective(ProcessCount record);

    int updateByPrimaryKey(ProcessCount record);

    int countAllProcessMeasure();

    List<ProcessCount> selectPCountCheckLeft(@Param("pCountCheckId") String pCountCheckId);
}