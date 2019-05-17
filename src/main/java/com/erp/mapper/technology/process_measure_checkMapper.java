package com.erp.mapper.technology;

import com.erp.bean.technology.process_measure_check;
import com.erp.bean.technology.process_measure_checkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface process_measure_checkMapper {
    long countByExample(process_measure_checkExample example);

    int deleteByExample(process_measure_checkExample example);

    int deleteByPrimaryKey(String pMeasureCheckId);

    int insert(process_measure_check record);

    int insertSelective(process_measure_check record);

    List<process_measure_check> selectByExample(process_measure_checkExample example);

    process_measure_check selectByPrimaryKey(String pMeasureCheckId);

    int updateByExampleSelective(@Param("record") process_measure_check record, @Param("example") process_measure_checkExample example);

    int updateByExample(@Param("record") process_measure_check record, @Param("example") process_measure_checkExample example);

    int updateByPrimaryKeySelective(process_measure_check record);

    int updateByPrimaryKey(process_measure_check record);
}