package com.erp.bean.quality;

import java.util.Date;

public class ProcessMeasure {
    private String pMeasureCheckId;

    private String processId;

    private String checkItem;

    private Date cdate;

    private String measureData;

    private String empId;

    private String result;

    private String note;

    private String empName;

    public ProcessMeasure() {
    }

    public ProcessMeasure(String pMeasureCheckId, String processId, String checkItem, Date cdate, String measureData, String empId, String result, String note, String empName) {
        this.pMeasureCheckId = pMeasureCheckId;
        this.processId = processId;
        this.checkItem = checkItem;
        this.cdate = cdate;
        this.measureData = measureData;
        this.empId = empId;
        this.result = result;
        this.note = note;
        this.empName = empName;
    }

    public String getpMeasureCheckId() {
        return pMeasureCheckId;
    }

    public void setpMeasureCheckId(String pMeasureCheckId) {
        this.pMeasureCheckId = pMeasureCheckId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(String checkItem) {
        this.checkItem = checkItem;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getMeasureData() {
        return measureData;
    }

    public void setMeasureData(String measureData) {
        this.measureData = measureData;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Override
    public String toString() {
        return "ProcessMeasure{" +
                "pMeasureCheckId='" + pMeasureCheckId + '\'' +
                ", processId='" + processId + '\'' +
                ", checkItem='" + checkItem + '\'' +
                ", cdate=" + cdate +
                ", measureData='" + measureData + '\'' +
                ", empId='" + empId + '\'' +
                ", result='" + result + '\'' +
                ", note='" + note + '\'' +
                ", empName='" + empName + '\'' +
                '}';
    }
}