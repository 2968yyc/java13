package com.erp.mapper.technology;

import com.erp.bean.technology.Process_measure_check;
import com.erp.bean.technology.Process_measure_checkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Process_measure_checkMapper {
    long countByExample(Process_measure_checkExample example);

    int deleteByExample(Process_measure_checkExample example);

    int deleteByPrimaryKey(String pMeasureCheckId);

    int insert(Process_measure_check record);

    int insertSelective(Process_measure_check record);

    List<Process_measure_check> selectByExample(Process_measure_checkExample example);

    Process_measure_check selectByPrimaryKey(String pMeasureCheckId);

    int updateByExampleSelective(@Param("record") Process_measure_check record, @Param("example") Process_measure_checkExample example);

    int updateByExample(@Param("record") Process_measure_check record, @Param("example") Process_measure_checkExample example);

    int updateByPrimaryKeySelective(Process_measure_check record);

    int updateByPrimaryKey(Process_measure_check record);
}