package cn.hs.checkitemgroup.pojo;

import java.util.Date;
import java.util.List;

public class CheckItemGroup extends CheckItemGroupKey {
    //新增所属检验设备名称字段
    private String equipmentName;

    //新增所含检验项目id字段
    private List<String> checkItemIdList;

    private String groupName;

    private String equipmentId;

    private String sampleType;

    private Integer orderNumber;

    private Date traceLog;

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public List<String> getCheckItemIdList() {
        return checkItemIdList;
    }

    public void setCheckItemIdList(List<String> checkItemIdList) {
        this.checkItemIdList = checkItemIdList;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId == null ? null : equipmentId.trim();
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