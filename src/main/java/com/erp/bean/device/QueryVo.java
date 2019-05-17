package com.erp.bean.device;


import java.util.List;

/**
 * @Author: yyc
 * @Date: 2019/5/17 17:49
 */
public class QueryVo {
    private int total;
    private List<Device> rows;

    public QueryVo() {
    }

    public QueryVo(int total, List<Device> rows) {
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Device> getRows() {
        return rows;
    }

    public void setRows(List<Device> rows) {
        this.rows = rows;
    }
}
