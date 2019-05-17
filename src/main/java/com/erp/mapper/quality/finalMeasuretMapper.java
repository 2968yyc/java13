package com.erp.mapper.quality;

import com.erp.bean.quality.finalMeasuret;
import com.erp.bean.quality.finalMeasuretExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface finalMeasuretMapper {
    long countByExample(finalMeasuretExample example);

    int deleteByExample(finalMeasuretExample example);

    int deleteByPrimaryKey(String fMeasureCheckId);

    int insert(finalMeasuret record);

    int insertSelective(finalMeasuret record);

    List<finalMeasuret> selectByExample(finalMeasuretExample example);

    finalMeasuret selectByPrimaryKey(String fMeasureCheckId);

    int updateByExampleSelective(@Param("record") finalMeasuret record, @Param("example") finalMeasuretExample example);

    int updateByExample(@Param("record") finalMeasuret record, @Param("example") finalMeasuretExample example);

    int updateByPrimaryKeySelective(finalMeasuret record);

    int updateByPrimaryKey(finalMeasuret record);
}