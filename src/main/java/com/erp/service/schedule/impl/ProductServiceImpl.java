package com.erp.service.schedule.impl;

import com.erp.bean.schedule.PageHander;
import com.erp.bean.schedule.Product;
import com.erp.bean.schedule.ProductExample;
import com.erp.mapper.schedule.ProductMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xf
 * @Date: 2019/5/17 17:09
 */
@Service
public class ProductServiceImpl {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public List<Product> findAllProduct(){
        return productMapper.selectAll(0,1000);
    }

    public PageHander findProduct(int page, int rows){

        PageHander pageHander=new PageHander();
        ProductExample productExample=new ProductExample();
        //ProductExample.Criteria criteria = ProductExample.createCriteria();
        int total=(int) productMapper.countByExample(productExample);
        pageHander.setTotal(total);

        int start=(page-1)*rows;
        if(start >= total){
            start= (total%rows==0?total/rows-1:total/rows)*rows;
        }
        List<Product> list= productMapper.selectAll(start,rows);
        pageHander.setRows(list);

        return pageHander;
    }


    public int insertProduct(Product product){
        return productMapper.insertSelective(product);
    }

    public int updateProduct(Product product){
        return productMapper.updateByPrimaryKeySelective(product);
    }

    public int deleteProductById(String productId){
        int result=productMapper.deleteByPrimaryKey(productId);
        return result;
    }
}
