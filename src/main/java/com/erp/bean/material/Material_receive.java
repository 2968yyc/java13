package com.erp.bean.material;

import java.util.Date;

public class Material_receive {
    private String receiveId;

    private String materialId;

    private Material material;

    private Integer amount;

    private Date receiveDate;

    private String sender;

    private String receiver;

    private String note;

    private String materialReceiveParams;

    public String getMaterialReceiveParams() {
        return materialReceiveParams;
    }

    public void setMaterialReceiveParams(String materialReceiveParams) {
        this.materialReceiveParams = materialReceiveParams;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId == null ? null : receiveId.trim();
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId == null ? null : materialId.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Material_receive(String receiveId, String materialId, Material material, Integer amount, Date receiveDate, String sender, String receiver, String note) {
        this.receiveId = receiveId;
        this.materialId = materialId;
        this.material = material;
        this.amount = amount;
        this.receiveDate = receiveDate;
        this.sender = sender;
        this.receiver = receiver;
        this.note = note;
    }

    public Material_receive() {
    }

    @Override
    public String toString() {
        return "Material_receive{" +
                "receiveId='" + receiveId + '\'' +
                ", materialId='" + materialId + '\'' +
                ", material=" + material +
                ", amount=" + amount +
                ", receiveDate=" + receiveDate +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}