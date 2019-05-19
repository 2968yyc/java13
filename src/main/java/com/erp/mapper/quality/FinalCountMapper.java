package com.erp.mapper.quality;

import com.erp.bean.quality.FinalCount;
import com.erp.bean.quality.FinalCountExample;
import java.util.List;
import com.erp.bean.quality.FinalMeasuret;
import org.apache.ibatis.annotations.Param;

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

    int countAllFinalCount();

    List<FinalMeasuret> selectFinalCountLeft(@Param("fCountCheckId") String fCountCheckId,@Param("orderId") String orderId);

    int countAllFinalCountBySomething(@Param("fCountCheckId") String fCountCheckId,@Param("orderId") String orderId);
}