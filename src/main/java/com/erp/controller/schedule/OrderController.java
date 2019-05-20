package com.erp.controller.schedule;

import com.erp.annotation.UpdateMethod;
import com.erp.bean.device.Info;
import com.erp.bean.schedule.PageHander;
import com.erp.bean.schedule.Order;
import com.erp.bean.user.Permission;
import com.erp.service.schedule.impl.OrderServiceImpl;
import com.erp.utils.PermissionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xf
 * @Date: 2019/5/18 15:06
 */
@Controller
@RequestMapping("order")
public class OrderController {



    @Autowired
    OrderServiceImpl orderService;

    @RequestMapping("get_data")
    @ResponseBody
    public List<Order> getData(){
        return orderService.findAllOrder();
    }

    @RequestMapping("get/{id}")
    @ResponseBody
    public Order queryOrder(@PathVariable("id") String id){
        return  orderService.queryOrder(id);
    }

    @RequestMapping("find")
    public String findOrder(){
        return "order_list";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageHander orderPage(int page, int rows){
         return orderService.findOrder(page,rows);
    }


    @RequestMapping("add_judge")
    public @ResponseBody Map<String,String> addOrder1(HttpServletRequest request){
        return PermissionUtils.permissionCheck("order:add",request);
    }

    @RequestMapping("add")
    public String addOrder2(){
        return "order_add";
    }

    @RequestMapping("insert")
    @ResponseBody
    public Info insertOrder(Order order){

        int i =orderService.insertOrder(order);
        Info info=new Info(200,"ok",null);
        return i!=0?info:new Info(2,"订单编号重复",null);
    }


    @RequestMapping("edit_judge")
    public @ResponseBody Map<String,String> editOrder1(HttpServletRequest request){
        return PermissionUtils.permissionCheck("order:edit",request);
    }

    @RequestMapping("edit")
    public String editOrder2(){
        return "order_edit";
    }

    @UpdateMethod("order")
    @RequestMapping("update_all")
    @ResponseBody
    public Info editOrder3(Order order){
        int i=orderService.updateOrder(order);
        Info info=new Info(200,"ok",null);
        return i==1?info:null;
    }



    @RequestMapping("delete_judge")
    public @ResponseBody Map<String,String> delOrder(HttpServletRequest request){
        return PermissionUtils.permissionCheck("order:delete",request);
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    public Info deleteOrder(String[] ids){
        int i =orderService.deleteOrderById(ids);
        Info info=new Info(200,"ok",null);
        return i!=0?info:null;
    }

    @RequestMapping("search_order_by_orderId")
    @ResponseBody
    public PageHander searchOrderById(String searchValue,int page,int rows){
        return orderService.searchById(searchValue,page,rows);
    }

    @RequestMapping("search_order_by_orderCustom")
    @ResponseBody
    public PageHander searchOrderByCustomName(String searchValue,int page,int rows){
        return orderService.searchByCustomName(searchValue,page,rows);
    }
    @RequestMapping("search_order_by_orderProduct")
    @ResponseBody
    public PageHander searchOrderByProductName(String searchValue,int page,int rows){
        return orderService.searchByProductName(searchValue,page,rows);
    }

 }
