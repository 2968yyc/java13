package model.admin;

import java.util.List;

public class PageHelper<T> {
    public static final int PAGE_SIZE = 5;
    private int totalRecordsNum; //总条目数
    private int currentPageNum; //当前页
    private int totalPageNum; //总页数
    private int prevPageNum; //前一页
    private int nextPageNum; //后一页
    private List<T> tList; //这里为什么要使用泛型？使用泛型意味着PageHelper可以在不同的场合下使用，而不是只能在分类的情况下使用

    public PageHelper() {
    }

    public PageHelper(int totalRecordsNum, int currentPageNum, int totalPageNum, int prevPageNum, int nextPageNum, List<T> tList) {
        this.totalRecordsNum = totalRecordsNum;
        this.currentPageNum = currentPageNum;
        this.totalPageNum = totalPageNum;
        this.prevPageNum = prevPageNum;
        this.nextPageNum = nextPageNum;
        this.tList = tList;
    }

    public int getTotalRecordsNum() {
        return totalRecordsNum;
    }

    public void setTotalRecordsNum(int totalRecordsNum) {
        this.totalRecordsNum = totalRecordsNum;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public int getPrevPageNum() {
        return prevPageNum;
    }

    public void setPrevPageNum(int prevPageNum) {
        this.prevPageNum = prevPageNum;
    }

    public int getNextPageNum() {
        return nextPageNum;
    }

    public void setNextPageNum(int nextPageNum) {
        this.nextPageNum = nextPageNum;
    }

    public List<T> gettList() {
        return tList;
    }

    public void settList(List<T> tList) {
        this.tList = tList;
    }

    @Override
    public String toString() {
        return "PageHelper{" +
                "totalRecordsNum=" + totalRecordsNum +
                ", currentPageNum=" + currentPageNum +
                ", totalPageNum=" + totalPageNum +
                ", prevPageNum=" + prevPageNum +
                ", nextPageNum=" + nextPageNum +
                ", tList=" + tList +
                '}';
    }
}
