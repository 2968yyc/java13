package com.erp.mapper.quality;

import com.erp.bean.quality.Unqualify;
import com.erp.bean.quality.UnqualifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UnqualifyMapper {
    long countByExample(UnqualifyExample example);

    int deleteByExample(UnqualifyExample example);

    int deleteByPrimaryKey(String unqualifyApplyId);

    int insert(Unqualify record);

    int insertSelective(Unqualify record);

    List<Unqualify> selectByExample(UnqualifyExample example);

    Unqualify selectByPrimaryKey(String unqualifyApplyId);

    int updateByExampleSelective(@Param("record") Unqualify record, @Param("example") UnqualifyExample example);

    int updateByExample(@Param("record") Unqualify record, @Param("example") UnqualifyExample example);

    int updateByPrimaryKeySelective(Unqualify record);

    int updateByPrimaryKey(Unqualify record);

    List<Unqualify> selectAllUnqualify();
}