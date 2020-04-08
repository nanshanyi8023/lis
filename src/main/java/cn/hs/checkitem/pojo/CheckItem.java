package cn.hs.checkitem.pojo;

import java.util.Date;

public class CheckItem extends CheckItemKey {

    private String itemName;

    private String englishAbbreviation;

    private String unit;

    private String itemType;

    private Integer upperReferenceValue;

    private Integer lowerReferenceValue;

    private String referenceValue;

    private String defaultValue;

    private Integer orderNumber;

    private Date traceLog;

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

    public Integer getUpperReferenceValue() {
        return upperReferenceValue;
    }

    public void setUpperReferenceValue(Integer upperReferenceValue) {
        this.upperReferenceValue = upperReferenceValue;
    }

    public Integer getLowerReferenceValue() {
        return lowerReferenceValue;
    }

    public void setLowerReferenceValue(Integer lowerReferenceValue) {
        this.lowerReferenceValue = lowerReferenceValue;
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