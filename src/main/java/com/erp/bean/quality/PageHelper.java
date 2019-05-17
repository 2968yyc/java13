package com.erp.bean.quality;

import java.util.List;

public class PageHelper<T> {
    public static final int PAGE_SIZE = 5;
    private int totalRecordsNum; //总条目数
    private int currentPageNum; //当前页
    private int totalPageNum; //总页数
    private int prevPageNum; //前一页
    private int nextPageNum; //后一页
    private List<T> records;

    public PageHelper() {
    }


}
