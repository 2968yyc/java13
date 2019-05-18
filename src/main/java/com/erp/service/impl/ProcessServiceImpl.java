package com.erp.service.impl;


import com.erp.bean.technology.Process;
import com.erp.bean.technology.ProcessExample;
import com.erp.mapper.technology.ProcessMapper;
import com.erp.service.ProcessService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
