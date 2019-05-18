package com.erp.service.impl;

import com.erp.bean.schedule.Custom;
import com.erp.bean.schedule.CustomExample;
import com.erp.bean.schedule.ManufactureExample;
import com.erp.bean.schedule.PageHander;
import com.erp.mapper.schedule.CustomMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xf
 * @Date: 2019/5/17 17:09
 */
@Service
public class CustomServiceImpl {
    @Autowired
    CustomMapper customMapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;
    public PageHander findCustom(int page,int rows){
        /*SqlSession sqlSession=sqlSessionFactory.openSession();
        CustomMapper mapper=sqlSession.getMapper(CustomMapper.class);*/

        PageHander pageHander=new PageHander();
        CustomExample customExample=new CustomExample();
        //CustomExample.Criteria criteria = customExample.createCriteria();
        int total=(int) customMapper.countByExample(customExample);
        pageHander.setTotal(total);

        int start=(page-1)*rows;
        if(start >= total){
            start= (total%rows==0?total/rows-1:total/rows)*rows;
        }
        List<Custom> list= customMapper.selectAll(start,rows);
        pageHander.setRows(list);

        return pageHander;
    }
}
