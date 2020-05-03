package cn.hs.checkitem.pojo;

import java.util.Date;
import java.util.List;

public class CheckItem extends CheckItemKey {

    //新增对应的检验设备id字段
    private List<String> equipmentIdList;

    //新增对应的检验项目组合id字段
    private List<String> checkItemGroupIdList;

    private String itemName;

    private String englishAbbreviation;

    private String unit;

    private String itemType;

    private Double lowerReferenceValue;

    private Double upperReferenceValue;

    private String referenceValue;

    private String defaultValue;

    private Integer orderNumber;

    private Date traceLog;

    public List<String> getEquipmentIdList() {
        return equipmentIdList;
    }

    public void setEquipmentIdList(List<String> equipmentIdList) {
        this.equipmentIdList = equipmentIdList;
    }

    public List<String> getCheckItemGroupIdList() {
        return checkItemGroupIdList;
    }

    public void setCheckItemGroupIdList(List<String> checkItemGroupIdList) {
        this.checkItemGroupIdList = checkItemGroupIdList;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getEnglishAbbreviation() {
        return englishAbbreviation;
    }

    public void setEnglishAbbreviation(String englishAbbreviation) {
        this.englishAbbreviation = englishAbbreviation == null ? null : englishAbbreviation.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType == null ? null : itemType.trim();
    }

    public Double getLowerReferenceValue() {
        return lowerReferenceValue;
    }

    public void setLowerReferenceValue(Double lowerReferenceValue) {
        this.lowerReferenceValue = lowerReferenceValue;
    }

    public Double getUpperReferenceValue() {
        return upperReferenceValue;
    }

    public void setUpperReferenceValue(Double upperReferenceValue) {
        this.upperReferenceValue = upperReferenceValue;
    }

    public String getReferenceValue() {
        return referenceValue;
    }

    public void setReferenceValue(String referenceValue) {
        this.referenceValue = referenceValue == null ? null : referenceValue.trim();
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
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