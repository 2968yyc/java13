package com.erp.service.schedule.impl;

import com.erp.bean.schedule.*;
import com.erp.mapper.schedule.CustomMapper;
import com.erp.mapper.schedule.OrderMapper;
import com.erp.mapper.schedule.ProductMapper;
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
public class OrderServiceImpl {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CustomMapper customMapper;
    @Autowired
    ProductMapper productMapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public List<Order> findAllOrder(){
        return orderMapper.selectAll(0,1000);
    }

    public Order queryOrder(String id){
        Order o=orderMapper.selectByPrimaryKey(id);
        String customId=o.getCustomId();
        Custom custom = customMapper.selectByPrimaryKey(customId);
        o.setCustom(custom);

        String productId=o.getProductId();
        Product product=productMapper.selectByPrimaryKey(productId);
        o.setProduct(product);

        return o;
    }

    public PageHander findOrder(int page, int rows){

        PageHander pageHander=new PageHander();
        OrderExample orderExample=new OrderExample();
        //OrderExample.Criteria criteria = OrderExample.createCriteria();
        int total=(int) orderMapper.countByExample(orderExample);
        pageHander.setTotal(total);

        int start=(page-1)*rows;
        if(start >= total){
            start= (total%rows==0?total/rows-1:total/rows)*rows;
        }
        List<Order> list= orderMapper.selectAll(start,rows);

        list=getList(list);
        pageHander.setRows(list);

        return pageHander;
    }


    public int insertOrder(Order order){
        return orderMapper.insertSelective(order);
    }

    public int updateOrder(Order order){
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    public int deleteOrderById(String[] orderId){
        OrderExample orderExample=new OrderExample();
        orderExample.or().andOrderIdIn(Arrays.asList(orderId));
        int result=orderMapper.deleteByExample(orderExample);
        return result;
    }

    public PageHander searchById(String searchValue,int page,int rows){
        PageHander pageHander=new PageHander();
        OrderExample orderExample=new OrderExample();
        if(searchValue.contains("\\") || searchValue.contains("%")){
            return null;
        }
        orderExample.createCriteria().andOrderIdLike("%"+searchValue+"%");
        int total=(int) orderMapper.countByExample(orderExample);
        pageHander.setTotal(total);

        PageHelper.startPage(page,rows);
        List<Order> list= orderMapper.selectByExample(orderExample);

        list=getList(list);
        pageHander.setRows(list);
        return pageHander;
    }

    //searchByCustomName
    public PageHander searchByCustomName(String searchValue,int page,int rows){
        PageHander pageHander=new PageHander();

        //查询那么对应的id数组
        CustomExample ce=new CustomExample();
        ce.or().andCustomNameLike("%"+searchValue+"%");
        List<Custom> customList=customMapper.selectByExample(ce);
        List<String> idList=new ArrayList<>();
        for(Custom c: customList){
            idList.add(c.getCustomId());
        }

       //查询包含以上id的oder
        OrderExample orderExample=new OrderExample();
        orderExample.createCriteria().andCustomIdIn(idList);
        int total=(int) orderMapper.countByExample(orderExample);
        pageHander.setTotal(total);

        PageHelper.startPage(page,rows);
        List<Order> list= orderMapper.selectByExample(orderExample);

        list=getList(list);
        pageHander.setRows(list);
        return pageHander;
    }

    //search_order_by_productName
    public PageHander searchByProductName(String searchValue,int page,int rows){
        PageHander pageHander=new PageHander();
        if(searchValue.contains("\\")){
            return null;
        }

        //查询那么对应的id数组
        ProductExample pe=new ProductExample();
        pe.or().andProductNameLike("%"+searchValue+"%");
        List<Product> productList=productMapper.selectByExample(pe);
        List<String> idList=new ArrayList<>();
        for(Product c: productList){
            idList.add(c.getProductId());
        }

        //查询包含以上id的oder
        OrderExample orderExample=new OrderExample();
        orderExample.createCriteria().andProductIdIn(idList);
        int total=(int) orderMapper.countByExample(orderExample);
        pageHander.setTotal(total);

        PageHelper.startPage(page,rows);
        List<Order> list= orderMapper.selectByExample(orderExample);

        list=getList(list);
        pageHander.setRows(list);
        return pageHander;
    }

    public List<Order> getList(List<Order> list){
        for (Order o:list) {
            String customId=o.getCustomId();
            Custom custom = customMapper.selectByPrimaryKey(customId);
            o.setCustom(custom);

            String productId=o.getProductId();
            Product product=productMapper.selectByPrimaryKey(productId);
            o.setProduct(product);
        }
        return list;
    }


}
