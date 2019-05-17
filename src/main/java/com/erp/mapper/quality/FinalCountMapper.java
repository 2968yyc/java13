package com.erp.mapper.quality;

import com.erp.bean.quality.FinalCount;
import com.erp.bean.quality.FinalCountExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinalCountMapper {
    long countByExample(FinalCountExample example);

    int deleteByExample(FinalCountExample example);

    int deleteByPrimaryKey(String fCountCheckId);

    int insert(FinalCount record);

    int insertSelective(FinalCount record);

    List<FinalCount> selectByExample(FinalCountExample example);

    FinalCount selectByPrimaryKey(String fCountCheckId);

    int updateByExampleSelective(@Param("record") FinalCount record, @Param("example") FinalCountExample example);

    int updateByExample(@Param("record") FinalCount record, @Param("example") FinalCountExample example);

    int updateByPrimaryKeySelective(FinalCount record);

    int updateByPrimaryKey(FinalCount record);
}