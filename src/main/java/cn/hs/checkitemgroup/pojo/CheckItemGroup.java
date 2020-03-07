package cn.hs.checkitemgroup.pojo;

import java.util.Date;

public class CheckItemGroup extends CheckItemGroupKey {
    private String groupName;

    private String workGroupId;

    private String samplingSite;

    private String sampleType;

    private Integer orderNumber;

    private Date traceLog;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getWorkGroupId() {
        return workGroupId;
    }

    public void setWorkGroupId(String workGroupId) {
        this.workGroupId = workGroupId == null ? null : workGroupId.trim();
    }

    public String getSamplingSite() {
        return samplingSite;
    }

    public void setSamplingSite(String samplingSite) {
        this.samplingSite = samplingSite == null ? null : samplingSite.trim();
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType == null ? null : sampleType.trim();
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getTraceLog() {
        return traceLog;
    }

    public void setTraceLog(Date traceLog) {
        this.traceLog = traceLog;
    }
}