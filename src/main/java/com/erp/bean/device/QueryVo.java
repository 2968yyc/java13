package com.erp.bean.device;


import java.util.List;

/**
 * @Author: yyc
 * @Date: 2019/5/17 17:49
 */
public  class  QueryVo <T> {
    private int total;
    private List<T> rows;

    public QueryVo() {
    }

    public QueryVo(int total, List<T> rows) {
        this.total = total;
        this.rows = rows;
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
}