package com.erp.service.schedule.impl;

import com.erp.bean.schedule.Manufacture;
import com.erp.bean.schedule.ManufactureExample;
import com.erp.bean.schedule.Order;
import com.erp.bean.schedule.PageHander;
import com.erp.bean.technology.Technology;
import com.erp.bean.technology.TechnologyExample;
import com.erp.mapper.schedule.ManufactureMapper;
import com.erp.mapper.schedule.OrderMapper;
import com.erp.mapper.technology.TechnologyMapper;
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
public class ManufactureServiceImpl {
    @Autowired
    ManufactureMapper manufactureMapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    TechnologyMapper technologyMapper;

    public List<Manufacture> findAllManufacture(){
        return manufactureMapper.selectAll(0,1000);
    }

    public Manufacture queryManufacture(String id){
        Manufacture m=manufactureMapper.selectByPrimaryKey(id);
        String orderId=m.getOrderId();
        Order order = orderMapper.selectByPrimaryKey(orderId);
        m.setcOrder(order);

        String productId=m.getTechnologyId();
        Technology technology=technologyMapper.selectByPrimaryKey(productId);
        m.setTechnology(technology);
        return m;
    }

    public PageHander findManufacture(int page, int rows){

        PageHander pageHander=new PageHander();
        ManufactureExample manufactureExample=new ManufactureExample();
        //ManufactureExample.Criteria criteria = ManufactureExample.createCriteria();
        int total=(int) manufactureMapper.countByExample(manufactureExample);
        pageHander.setTotal(total);

        int start=(page-1)*rows;
        if(start >= total){
            start= (total%rows==0?total/rows-1:total/rows)*rows;
        }
        List<Manufacture> list= manufactureMapper.selectAll(start,rows);

        list=getList(list);

        pageHander.setRows(list);

        return pageHander;
    }


    public int insertManufacture(Manufacture manufacture){
        int i=0;
        try {
            i=manufactureMapper.insertSelective(manufacture);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public int updateManufacture(Manufacture manufacture){
        return manufactureMapper.updateByPrimaryKeySelective(manufacture);
    }

    public int deleteManufactureById(String[] manufactureId){
        ManufactureExample manufactureExample=new ManufactureExample();
        manufactureExample.or().andManufactureSnIn(Arrays.asList(manufactureId));
        int result=manufactureMapper.deleteByExample(manufactureExample);
        return result;
    }

    public PageHander searchById(String searchValue,int page,int rows){
        PageHander pageHander=new PageHander();
        ManufactureExample manufactureExample=new ManufactureExample();

        manufactureExample.or().andManufactureSnLike("%"+searchValue+"%");
        int total=(int) manufactureMapper.countByExample(manufactureExample);
        pageHander.setTotal(total);

        PageHelper.startPage(page,rows);
        List<Manufacture> list= manufactureMapper.selectByExample(manufactureExample);

        list=getList(list);
        pageHander.setRows(list);
        return pageHander;
    }

    public PageHander searchByOrderId(String searchValue,int page,int rows){
        PageHander pageHander=new PageHander();
        ManufactureExample manufactureExample=new ManufactureExample();

        manufactureExample.or().andOrderIdLike("%"+searchValue+"%");
        int total=(int) manufactureMapper.countByExample(manufactureExample);
        pageHander.setTotal(total);

        PageHelper.startPage(page,rows);
        List<Manufacture> list= manufactureMapper.selectByExample(manufactureExample);

        list=getList(list);
        pageHander.setRows(list);
        return pageHander;
    }

    public PageHander searchByTechnologyName(String searchValue,int page,int rows){
        PageHander pageHander=new PageHander();
        TechnologyExample te=new TechnologyExample();
        te.or().andTechnologyNameLike("%"+searchValue+"%");
        List<Technology> technologies=technologyMapper.selectByExample(te);
        List<String> names =new ArrayList<>();
        for(Technology o: technologies){
            names.add(o.getTechnologyId());
        }
        if(names.size()!=0){
            ManufactureExample manufactureExample=new ManufactureExample();
            manufactureExample.or().andTechnologyIdIn(names);
            int total=(int) manufactureMapper.countByExample(manufactureExample);
            pageHander.setTotal(total);

            PageHelper.startPage(page,rows);
            List<Manufacture> list= manufactureMapper.selectByExample(manufactureExample);

            list=getList(list);
            pageHander.setRows(list);
        }else{
            pageHander.setRows(new ArrayList());
        }



        return pageHander;
    }


    public List<Manufacture> getList(List<Manufacture> list){
        for (Manufacture o:list) {
            String orderId=o.getOrderId();
            Order order = orderMapper.selectByPrimaryKey(orderId);
            o.setcOrder(order);

            String productId=o.getTechnologyId();
            Technology technology=technologyMapper.selectByPrimaryKey(productId);
            o.setTechnology(technology);
        }

        return list;
    }

}
