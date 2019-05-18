package com.erp.service;

import com.erp.bean.technology.Process;

import java.util.List;

/**
 * @Author : zjf
 * @Date : 2019/5/18 下午 02:17
 */
public interface ProcessService {
    List<Process> findAllByPage(Integer page, Integer rows);
}
