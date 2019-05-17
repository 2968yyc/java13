package com.erp.bean;
import com.erp.bean.material.Material;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @Author: Qiu
 * @Date: 2019/5/17 20:50
 */
public class QueryVO<T>{
    
    Integer total;
    List<T> rows;

    public QueryVO(Integer total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public QueryVO() {
    }

    @Override
    public String toString() {
        return "QueryVO{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }

    public Integer gettotal() {
        return total;
    }

    public void settotal(Integer total) {
        this.total = total;
    }

    public List<T> getrows() {
        return rows;
    }

    public void setrows(List<T> rows) {
        this.rows = rows;
    }
}
