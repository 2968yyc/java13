package com.erp.bean.technology;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author : zjf
 * @Date : 2019/5/17 下午 09:43
 */
public class PageHelper<T> {
    private List<T> rows;
    private int total;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageHelper{" +
                "rows=" + rows +
                ", total=" + total +
                '}';
    }
}
