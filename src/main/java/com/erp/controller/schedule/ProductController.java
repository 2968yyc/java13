package com.erp.controller.schedule;

import com.erp.bean.device.Info;
import com.erp.bean.schedule.PageHander;
import com.erp.bean.schedule.Product;
import com.erp.service.schedule.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: xf
 * @Date: 2019/5/18 15:06
 */
@Controller
@RequestMapping("product")
public class ProductController {



    @Autowired
    ProductServiceImpl productService;

    @RequestMapping("get_data")
    @ResponseBody
    public List<Product> getData(){
        return productService.findAllProduct();
    }

    @RequestMapping("get/{id}")
    @ResponseBody
    public Product queryProduct(@PathVariable("id") String id){
        return  productService.queryProduct(id);
    }

    @RequestMapping("find")
    public String findProduct(){
        return "product_list";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageHander productPage(int page, int rows){
         return productService.findProduct(page,rows);
    }


    @RequestMapping("add_judge")
    public String addProduct1(){
        return "product_list";
    }

    @RequestMapping("add")
    public String addProduct2(){
        return "product_add";
    }

    @RequestMapping("insert")
    @ResponseBody
    public Info insertProduct(Product product){
        int i =productService.insertProduct(product);
        Info info=new Info(200,"ok",null);
        return i!=0?info:null;
    }


    @RequestMapping("edit_judge")
    public String editProduct1(){
        return "product_list";
    }

    @RequestMapping("edit")
    public String editProduct2(){
        return "product_edit";
    }
    @RequestMapping("update_all")
    @ResponseBody
    public Info editProduct3(Product product){
        int i=productService.updateProduct(product);
        Info info=new Info(200,"ok",null);
        return i==1?info:null;
    }



    @RequestMapping("delete_judge")
    public String delProduct(){
        return "product_list";
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    public Info deleteProduct(String ids){
        int i =productService.deleteProductById(ids);
        Info info=new Info(200,"ok",null);
        return i==1?info:null;
    }

    @RequestMapping("search_product_by_productId")
    @ResponseBody
    public PageHander searchProductById(String searchValue,int page,int rows){
        return productService.searchById(searchValue,page,rows);
    }
    @RequestMapping("search_product_by_productName")
    @ResponseBody
    public PageHander searchProductByName(String searchValue,int page,int rows){
        return productService.searchByName(searchValue,page,rows);
    }
    @RequestMapping("search_product_by_productType")
    @ResponseBody
    public PageHander searchProductByType(String searchValue,int page,int rows){
        return productService.searchByType(searchValue,page,rows);
    }
 }
