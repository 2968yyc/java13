package com.erp.bean.schedule;

import java.util.List;

/**
 * @Author: xf
 * @Date: 2019/5/17 21:47
 */
public class PageHander<T> {
    private int total;
    private List<T> rows;

    public PageHander() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageHander{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
