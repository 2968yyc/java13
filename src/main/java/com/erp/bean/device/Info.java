package com.erp.bean.device;

/**
 * @Author: yyc
 * @Date: 2019/5/17 21:25
 */
public class Info {
    private int status;
    private String msg;
    private Object data;

    public Info() {
    }

    public Info(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
