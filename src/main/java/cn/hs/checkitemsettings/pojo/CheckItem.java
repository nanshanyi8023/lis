package cn.hs.checkitemsettings.pojo;

import java.util.Date;

public class CheckItem extends CheckItemKey {
    private String itemName;

    private String englishAbbreviations;

    private String unit;

    private String itemType;

    private String pySpell;

    private String wbSpell;

    private Date tracelog;

    private Integer orderNumber;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getEnglishAbbreviations() {
        return englishAbbreviations;
    }

    public void setEnglishAbbreviations(String englishAbbreviations) {
        this.englishAbbreviations = englishAbbreviations == null ? null : englishAbbreviations.trim();
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

    public String getPySpell() {
        return pySpell;
    }

    public void setPySpell(String pySpell) {
        this.pySpell = pySpell == null ? null : pySpell.trim();
    }

    public String getWbSpell() {
        return wbSpell;
    }

    public void setWbSpell(String wbSpell) {
        this.wbSpell = wbSpell == null ? null : wbSpell.trim();
    }

    public Date getTracelog() {
        return tracelog;
    }

    public void setTracelog(Date tracelog) {
        this.tracelog = tracelog;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}