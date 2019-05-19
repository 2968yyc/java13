package com.erp.mapper.quality;

import com.erp.bean.quality.FinalMeasuret;
import com.erp.bean.quality.FinalMeasuretExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FinalMeasuretMapper {
    long countByExample(FinalMeasuretExample example);

    int deleteByExample(FinalMeasuretExample example);

    int deleteByPrimaryKey(String fMeasureCheckId);

    int insert(FinalMeasuret record);

    int insertSelective(FinalMeasuret record);

    List<FinalMeasuret> selectByExample(FinalMeasuretExample example);

    FinalMeasuret selectByPrimaryKey(String fMeasureCheckId);

    int updateByExampleSelective(@Param("record") FinalMeasuret record, @Param("example") FinalMeasuretExample example);

    int updateByExample(@Param("record") FinalMeasuret record, @Param("example") FinalMeasuretExample example);

    int updateByPrimaryKeySelective(FinalMeasuret record);

    int updateByPrimaryKey(FinalMeasuret record);

    /*添加的方法*/

    int countAllFinalMeasure();

    List<FinalMeasuret> selectAllFMeasureCheckLeft();

    List<FinalMeasuret> searchFMeasureCheckByFMeasureCheckIdLeft(@Param("fMeasureCheckId") String searchValue);

    List<FinalMeasuret> searchfMeasureCheckByOrderIdLeft(@Param("orderId") String searchValue);
}