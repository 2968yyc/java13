package com.erp.bean.schedule;

import java.util.List;

/**
 * @Author: xf
 * @Date: 2019/5/17 21:47
 */
public class PageHander {
    private int total;
    private List<Custom> rows;

    public PageHander() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Custom> getRows() {
        return rows;
    }

    public void setRows(List<Custom> rows) {
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
