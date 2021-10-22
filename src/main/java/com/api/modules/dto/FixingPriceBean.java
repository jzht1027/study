package com.api.modules.dto;

/**
 * @ClassName FixingPriceBean
 * @Description
 * @Author
 * @Date 2021/6/28 13:28
 * @Version 1.0
 **/

public class FixingPriceBean {
    private String deskentityId;
    private String uploadType;//FIXING
    private String eodDate;
    private String exchId;
    private String stkId;
    private String price;
    private String src;
    private String sfFlag;
    private String updateTime;
    private String underlyingName;//标的名称
    private String deskEntityName;//业务组名称
    private String type; //VR
    private String generalEodDate;
    private String generalUpdateTime;

    public String getDeskentityId() {
        return deskentityId;
    }
    public void setDeskentityId(String deskentityId) {
        this.deskentityId = deskentityId;
    }
    public String getUploadType() {
        return uploadType;
    }
    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }
    public String getEodDate() {
        return eodDate;
    }
    public void setEodDate(String eodDate) {
        this.eodDate = eodDate;
    }
    public String getExchId() {
        return exchId;
    }
    public void setExchId(String exchId) {
        this.exchId = exchId;
    }
    public String getStkId() {
        return stkId;
    }
    public void setStkId(String stkId) {
        this.stkId = stkId;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getSrc() {
        return src;
    }
    public void setSrc(String src) {
        this.src = src;
    }
    public String getSfFlag() {
        return sfFlag;
    }
    public void setSfFlag(String sfFlag) {
        this.sfFlag = sfFlag;
    }
    public String getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public String getDeskEntityName() {
        return deskEntityName;
    }
    public void setDeskEntityName(String deskEntityName) {
        this.deskEntityName = deskEntityName;
    }
    public String getUnderlyingName() {
        return underlyingName;
    }
    public void setUnderlyingName(String underlyingName) {
        this.underlyingName = underlyingName;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getGeneralEodDate() {
        return generalEodDate;
    }
    public void setGeneralEodDate(String generalEodDate) {
        this.generalEodDate = generalEodDate;
    }
    public String getGeneralUpdateTime() {
        return generalUpdateTime;
    }
    public void setGeneralUpdateTime(String generalUpdateTime) {
        this.generalUpdateTime = generalUpdateTime;
    }
}
