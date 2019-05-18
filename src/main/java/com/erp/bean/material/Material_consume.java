package com.erp.bean.material;

import java.util.Date;

public class Material_consume {
    private String consumeId;

    private String workId;

    private String materialId;

    private Material material;


    private Integer consumeAmount;

    private Date consumeDate;

    private String sender;

    private String receiver;

    private String note;

    private String materialConsumeParams;

    public String getMaterialConsumeParams() {
        return materialConsumeParams;
    }

    public void setMaterialConsumeParams(String materialConsumeParams) {
        this.materialConsumeParams = materialConsumeParams;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(String consumeId) {
        this.consumeId = consumeId == null ? null : consumeId.trim();
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId == null ? null : workId.trim();
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId == null ? null : materialId.trim();
    }

    public Integer getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Integer consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public Date getConsumeDate() {
        return consumeDate;
    }

    public void setConsumeDate(Date consumeDate) {
        this.consumeDate = consumeDate;
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

    public Material_consume() {
    }

    @Override
    public String toString() {
        return "Material_consume{" +
                "consumeId='" + consumeId + '\'' +
                ", workId='" + workId + '\'' +
                ", materialId='" + materialId + '\'' +
                ", material=" + material +
                ", consumeAmount=" + consumeAmount +
                ", consumeDate=" + consumeDate +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public Material_consume(String consumeId, String workId, String materialId, Material material, Integer consumeAmount, Date consumeDate, String sender, String receiver, String note) {
        this.consumeId = consumeId;
        this.workId = workId;
        this.materialId = materialId;
        this.material = material;
        this.consumeAmount = consumeAmount;
        this.consumeDate = consumeDate;
        this.sender = sender;
        this.receiver = receiver;
        this.note = note;
    }
}