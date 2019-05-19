package com.erp.service.technology.impl;


import com.erp.bean.device.Info;
import com.erp.bean.technology.Process;
import com.erp.bean.technology.ProcessExample;
import com.erp.mapper.technology.ProcessMapper;
import com.erp.service.technology.ProcessService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * @Author : zjf
 * @Date : 2019/5/18 下午 02:17
 */
@Service
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    ProcessMapper processMapper;
    @Override
    public List<Process> findAllByPage(Integer page, Integer rows) {
        ProcessExample processExample = new ProcessExample();
        ProcessExample.Criteria criteria = processExample.createCriteria();
        criteria.getAllCriteria();
        PageHelper.startPage(page,rows);
        List<Process> processes = processMapper.selectByExample(processExample);
        return processes;
    }

    @Override
    public Process queryProcess(String id) {
        Process process = processMapper.selectByPrimaryKey(id);
        return process;
    }

    @Override
    public int deleteByids(String[] ids) {
        ProcessExample processExample = new ProcessExample();
        ProcessExample.Criteria criteria = processExample.createCriteria();
        List<String> lists = Arrays.asList(ids);
        criteria.andProcessIdIn(lists);
        int i = processMapper.deleteByExample(processExample);
        if(i < 1){
            return 0;
        }
        return 1;
    }

    @Override
    public int addByProcess(Process process) {
        int insert = processMapper.insert(process);
        return insert;
    }

    @Override
    public int editByProcess(Process process) {
        int update = processMapper.updateByPrimaryKey(process);
        return update;
    }

    @Override
    public List<Process> searchAllByPage(Integer page, Integer rows, String searchValue) {
        ProcessExample processExample = new ProcessExample();
        ProcessExample.Criteria criteria = processExample.createCriteria();
        criteria.andProcessIdLike("%" + searchValue + "%");
        PageHelper.startPage(page, rows);
        List<Process> processes = processMapper.selectByExample(processExample);
        return processes;
    }

    @Override
    public List<Process> searchAllById(Integer page, Integer rows, String searchValue) {
        ProcessExample processExample = new ProcessExample();
        ProcessExample.Criteria criteria = processExample.createCriteria();
        criteria.andTechnologyPlanIdLike("%" + searchValue + "%");
        PageHelper.startPage(page, rows);
        List<Process> processes = processMapper.selectByExample(processExample);
        return processes;
    }
}
