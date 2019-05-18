package com.erp.service.schedule.impl;

import com.erp.bean.schedule.Work;
import com.erp.bean.schedule.WorkExample;
import com.erp.bean.schedule.PageHander;
import com.erp.mapper.schedule.WorkMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xf
 * @Date: 2019/5/17 17:09
 */
@Service
public class WorkServiceImpl {
    @Autowired
    WorkMapper workMapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public List<Work> findAllWork(){
        return workMapper.selectAll(0,1000);
    }

    public PageHander findWork(int page, int rows){

        PageHander pageHander=new PageHander();
        WorkExample workExample=new WorkExample();
        //WorkExample.Criteria criteria = WorkExample.createCriteria();
        int total=(int) workMapper.countByExample(workExample);
        pageHander.setTotal(total);

        int start=(page-1)*rows;
        if(start >= total){
            start= (total%rows==0?total/rows-1:total/rows)*rows;
        }
        List<Work> list= workMapper.selectAll(start,rows);
        pageHander.setRows(list);

        return pageHander;
    }


    public int insertWork(Work work){
        return workMapper.insertSelective(work);
    }

    public int updateWork(Work work){
        return workMapper.updateByPrimaryKeySelective(work);
    }

    public int deleteWorkById(String workId){
        int result=workMapper.deleteByPrimaryKey(workId);
        return result;
    }
}
