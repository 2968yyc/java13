package com.erp.service.schedule.impl;

import com.erp.bean.schedule.PageHander;
import com.erp.bean.schedule.Product;
import com.erp.bean.schedule.ProductExample;
import com.erp.mapper.schedule.ProductMapper;
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
public class ProductServiceImpl {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;


    public List<Product> findAllProduct(){
        return productMapper.selectAll(0,1000);
    }

    public Product queryProduct(String id){
        return productMapper.selectByPrimaryKey(id);
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
        int i=0;
        try {
            i=productMapper.insertSelective(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public int updateProduct(Product product){
        return productMapper.updateByPrimaryKeySelective(product);
    }

    public int deleteProductById(String[] productId){
        ProductExample productExample=new ProductExample();
        productExample.or().andProductIdIn(Arrays.asList(productId));
        int result=productMapper.deleteByExample(productExample);
        return result;
    }

    public PageHander searchById(String searchValue,int page,int rows){
        PageHander pageHander=new PageHander();
        ProductExample productExample=new ProductExample();
        if(searchValue.contains("\\") || searchValue.contains("%")){
            return null;
        }
        productExample.createCriteria().andProductIdLike("%"+searchValue+"%");
        int total=(int) productMapper.countByExample(productExample);
        pageHander.setTotal(total);

        PageHelper.startPage(page,rows);
        List<Product> list= productMapper.selectByExample(productExample);
        pageHander.setRows(list);
        return pageHander;
    }

    public PageHander searchByName(String searchValue,int page,int rows){
        PageHander pageHander=new PageHander();
        ProductExample productExample=new ProductExample();
        if(searchValue.contains("\\") || searchValue.contains("%")){
            return null;
        }
        productExample.or().andProductNameLike("%"+searchValue+"%");
        int total=(int) productMapper.countByExample(productExample);
        pageHander.setTotal(total);

        PageHelper.startPage(page,rows);
        List<Product> list= productMapper.selectByExample(productExample);
        pageHander.setRows(list);
        return pageHander;
    }

    public PageHander searchByType(String searchValue,int page,int rows){
        PageHander pageHander=new PageHander();
        ProductExample productExample=new ProductExample();
        productExample.or().andProductTypeLike("%"+searchValue+"%");
        int total=(int) productMapper.countByExample(productExample);
        pageHander.setTotal(total);

        PageHelper.startPage(page,rows);
        List<Product> list= productMapper.selectByExample(productExample);
        pageHander.setRows(list);
        return pageHander;
    }

}
