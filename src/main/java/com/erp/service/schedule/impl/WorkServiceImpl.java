package com.erp.service.schedule.impl;

import com.erp.bean.device.Device;
import com.erp.bean.device.DeviceExample;
import com.erp.bean.schedule.*;
import com.erp.bean.technology.Process;
import com.erp.mapper.device.DeviceMapper;
import com.erp.mapper.schedule.ProductMapper;
import com.erp.mapper.schedule.WorkMapper;
import com.erp.mapper.technology.ProcessMapper;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
    @Autowired
    ProcessMapper processMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    DeviceMapper deviceMapper;

    public List<Work> findAllWork(){
        return workMapper.selectAll(0,1000);
    }

    public Work queryWork(String id){
        Work o=workMapper.selectByPrimaryKey(id);
        String processId=o.getProcessId();
        Process process = processMapper.selectByPrimaryKey(processId);
        o.setProcess(process);

        String productId=o.getProductId();
        Product product=productMapper.selectByPrimaryKey(productId);
        o.setProduct(product);

        String deviceId=o.getDeviceId();
        Device device=deviceMapper.selectByPrimaryKey(deviceId);
        o.setDevice(device);

        return o;
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
        list=getList(list);

        pageHander.setRows(list);

        return pageHander;
    }


    public int insertWork(Work work){
        int i=0;
        try {
            i=workMapper.insertSelective(work);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public int updateWork(Work work){
        return workMapper.updateByPrimaryKeySelective(work);
    }

    public int deleteWorkById(String[] workId){
        WorkExample workExample=new WorkExample();
        workExample.or().andWorkIdIn(Arrays.asList(workId));
        int result=workMapper.deleteByExample(workExample);
        return result;
    }

    public PageHander searchById(String searchValue,int page,int rows){
        PageHander pageHander=new PageHander();
        WorkExample workExample=new WorkExample();

        workExample.createCriteria().andWorkIdLike("%"+searchValue+"%");
        int total=(int) workMapper.countByExample(workExample);
        pageHander.setTotal(total);

        PageHelper.startPage(page,rows);
        List<Work> list= workMapper.selectByExample(workExample);

        list=getList(list);
        pageHander.setRows(list);
        return pageHander;
    }

    public PageHander searchByProcessId(String searchValue,int page,int rows){
        PageHander pageHander=new PageHander();
        WorkExample workExample=new WorkExample();

        workExample.or().andProcessIdLike("%"+searchValue+"%");
        int total=(int) workMapper.countByExample(workExample);
        pageHander.setTotal(total);

        PageHelper.startPage(page,rows);
        List<Work> list= workMapper.selectByExample(workExample);

        list=getList(list);
        pageHander.setRows(list);
        return pageHander;
    }

    public PageHander searchByProductName(String searchValue,int page,int rows){
        PageHander pageHander=new PageHander();

        ProductExample pe=new ProductExample();
        pe.or().andProductNameLike("%"+searchValue+"%");
        List<Product> productList=productMapper.selectByExample(pe);
        List<String> idList=new ArrayList<>();
        for(Product c: productList){
            idList.add(c.getProductId());
        }

        if(idList.size()!=0){
            WorkExample workExample=new WorkExample();
            workExample.or().andProductIdIn(idList);

            int total=(int) workMapper.countByExample(workExample);
            pageHander.setTotal(total);

            PageHelper.startPage(page,rows);
            List<Work> list= workMapper.selectByExample(workExample);

            list=getList(list);
            pageHander.setRows(list);

        }else{
            pageHander.setRows(new ArrayList());
        }



        return pageHander;
    }

    public PageHander searchByDeviceName(String searchValue,int page,int rows){
        PageHander pageHander=new PageHander();

        DeviceExample de=new DeviceExample();
        de.or().andDeviceNameLike("%"+searchValue+"%");
        List<Device> deviceList=deviceMapper.selectByExample(de);
        List<String> idList=new ArrayList<>();
        for(Device c: deviceList){
            idList.add(c.getDeviceId());
        }
        if(idList.size()!=0){
            WorkExample workExample=new WorkExample();
            workExample.or().andDeviceIdIn(idList);

            int total=(int) workMapper.countByExample(workExample);
            pageHander.setTotal(total);

            PageHelper.startPage(page,rows);
            List<Work> list= workMapper.selectByExample(workExample);

            list=getList(list);
            pageHander.setRows(list);
        }else{
            pageHander.setRows(new ArrayList());
        }

        return pageHander;
    }



    public List<Work> getList(List<Work> list){
        for (Work o:list) {
            String processId=o.getProcessId();
            Process process = processMapper.selectByPrimaryKey(processId);
            o.setProcess(process);

            String productId=o.getProductId();
            Product product=productMapper.selectByPrimaryKey(productId);
            o.setProduct(product);

            String deviceId=o.getDeviceId();
            Device device=deviceMapper.selectByPrimaryKey(deviceId);
            o.setDevice(device);

        }
        return list;
    }


}
