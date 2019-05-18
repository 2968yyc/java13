package com.erp.bean;

/**
 * @Author: yyc
 * @Date: 2019/5/18 22:52
 */
public class UploadInfo {
    private int error;
    private String url;

    public UploadInfo() {
    }

    public UploadInfo(int error, String url) {
        this.error = error;
        this.url = url;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
