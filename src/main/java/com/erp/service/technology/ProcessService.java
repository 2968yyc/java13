package com.erp.service.technology;

import com.erp.bean.technology.Process;

import java.util.List;

/**
 * @Author : zjf
 * @Date : 2019/5/18 下午 02:17
 */
public interface ProcessService {
    List<Process> findAllByPage(Integer page, Integer rows);

    Process queryProcess(String id);

    int deleteByids(String[] ids);

    int addByProcess(Process process);

    int editByProcess(Process process);

    List<Process> searchAllByPage(Integer page, Integer rows, String searchValue);

    List<Process> searchAllById(Integer page, Integer rows, String searchValue);

    List<Process> queryAllProcess();
}
