package com.erp.service.schedule.impl;

import com.erp.bean.schedule.PageHander;
import com.erp.bean.schedule.Task;
import com.erp.bean.schedule.TaskExample;
import com.erp.mapper.schedule.TaskMapper;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: xf
 * @Date: 2019/5/17 17:09
 */
@Service
public class TaskServiceImpl {
    @Autowired
    TaskMapper taskMapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public List<Task> findAllTask(){
        return taskMapper.selectAll(0,1000);
    }

    public Task queryTask(String id){
        return taskMapper.selectByPrimaryKey(id);
    }

    public PageHander findTask(int page, int rows){

        PageHander pageHander=new PageHander();
        TaskExample taskExample=new TaskExample();
        //TaskExample.Criteria criteria = TaskExample.createCriteria();
        int total=(int) taskMapper.countByExample(taskExample);
        pageHander.setTotal(total);

        int start=(page-1)*rows;
        if(start >= total){
            start= (total%rows==0?total/rows-1:total/rows)*rows;
        }
        List<Task> list= taskMapper.selectAll(start,rows);
        pageHander.setRows(list);

        return pageHander;
    }


    public int insertTask(Task task){
        int i=0;
        try {
            i=taskMapper.insertSelective(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public int updateTask(Task task){
        return taskMapper.updateByPrimaryKeySelective(task);
    }

    public int deleteTaskById(String[] taskId){
        TaskExample taskExample=new TaskExample();
        taskExample.or().andTaskIdIn(Arrays.asList(taskId));
        int result=taskMapper.deleteByExample(taskExample);
        return result;
    }

    public PageHander searchById(String searchValue,int page,int rows){
        PageHander pageHander=new PageHander();
        TaskExample taskExample=new TaskExample();

        taskExample.createCriteria().andTaskIdLike("%"+searchValue+"%");
        int total=(int) taskMapper.countByExample(taskExample);
        pageHander.setTotal(total);

        PageHelper.startPage(page,rows);
        List<Task> list= taskMapper.selectByExample(taskExample);
        pageHander.setRows(list);
        return pageHander;
    }

    public PageHander searchByWorkId(String searchValue,int page,int rows){
        PageHander pageHander=new PageHander();
        TaskExample taskExample=new TaskExample();

        taskExample.or().andWorkIdLike("%"+searchValue+"%");
        int total=(int) taskMapper.countByExample(taskExample);
        pageHander.setTotal(total);

        PageHelper.startPage(page,rows);
        List<Task> list= taskMapper.selectByExample(taskExample);
        pageHander.setRows(list);
        return pageHander;
    }

    public PageHander searchByManufactureSn(String searchValue,int page,int rows){
        PageHander pageHander=new PageHander();
        TaskExample taskExample=new TaskExample();

        taskExample.or().andManufactureSnLike("%"+searchValue+"%");
        int total=(int) taskMapper.countByExample(taskExample);
        pageHander.setTotal(total);

        PageHelper.startPage(page,rows);
        List<Task> list= taskMapper.selectByExample(taskExample);
        pageHander.setRows(list);
        return pageHander;
    }

}
