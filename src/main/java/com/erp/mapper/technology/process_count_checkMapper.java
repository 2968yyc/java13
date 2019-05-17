package com.erp.mapper.technology;

import com.erp.bean.technology.process_count_check;
import com.erp.bean.technology.process_count_checkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface process_count_checkMapper {
    long countByExample(process_count_checkExample example);

    int deleteByExample(process_count_checkExample example);

    int deleteByPrimaryKey(String pCountCheckId);

    int insert(process_count_check record);

    int insertSelective(process_count_check record);

    List<process_count_check> selectByExample(process_count_checkExample example);

    process_count_check selectByPrimaryKey(String pCountCheckId);

    int updateByExampleSelective(@Param("record") process_count_check record, @Param("example") process_count_checkExample example);

    int updateByExample(@Param("record") process_count_check record, @Param("example") process_count_checkExample example);

    int updateByPrimaryKeySelective(process_count_check record);

    int updateByPrimaryKey(process_count_check record);
}