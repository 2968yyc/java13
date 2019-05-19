package com.erp.service.schedule.impl;

import com.erp.bean.schedule.Custom;
import com.erp.bean.schedule.CustomExample;
import com.erp.bean.schedule.PageHander;
import com.erp.mapper.schedule.CustomMapper;
import com.github.pagehelper.PageHelper;
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

    public List<Custom> findAllOrder(){
        return customMapper.selectAll(0,1000);
    }

    public Custom queryCustom(String id){
        return customMapper.selectByPrimaryKey(id);
    }

    public PageHander findCustom(int page, int rows){
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


    public int insertCustom(Custom custom){
        return customMapper.insertSelective(custom);
    }

    public int updateCustom(Custom custom){
        return customMapper.updateByPrimaryKeySelective(custom);
    }

    public int deleteCustomById(String customId){
        int result=customMapper.deleteByPrimaryKey(customId);
        return result;
    }

    public PageHander searchById(String searchValue,int page,int rows){
        PageHander pageHander=new PageHander();
        CustomExample customExample=new CustomExample();
        if(searchValue.contains("\\") || searchValue.contains("%")){
            return null;
        }
        customExample.createCriteria().andCustomIdLike("%"+searchValue+"%");
        int total=(int) customMapper.countByExample(customExample);
        pageHander.setTotal(total);

        PageHelper.startPage(page,rows);
        List<Custom> list= customMapper.selectByExample(customExample);
        pageHander.setRows(list);
        return pageHander;
    }

    public PageHander searchByName(String searchValue,int page,int rows){
        PageHander pageHander=new PageHander();
        CustomExample customExample=new CustomExample();
        if(searchValue.contains("\\") || searchValue.contains("%")){
            return null;
        }
        customExample.or().andCustomNameLike("%"+searchValue+"%");
        int total=(int) customMapper.countByExample(customExample);
        pageHander.setTotal(total);

        PageHelper.startPage(page,rows);
        List<Custom> list= customMapper.selectByExample(customExample);
        pageHander.setRows(list);
        return pageHander;
    }
}
