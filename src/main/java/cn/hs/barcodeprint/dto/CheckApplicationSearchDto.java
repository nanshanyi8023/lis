package cn.hs.barcodeprint.dto;

import java.util.List;

public class CheckApplicationSearchDto {
    private List<String> patientIdList;   //患者id(多个患者)
    private String startDate;             //开始时间
    private String endDate;               //结束时间
    private int pageIndex;             //当前页
    private int pageSize;              //每页展示条数
    private int beginItem;             //起始条目

    public List<String> getPatientIdList() {
        return patientIdList;
    }

    public void setPatientIdList(List<String> patientIdList) {
        this.patientIdList = patientIdList;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getBeginItem() {
        return beginItem;
    }

    public void setBeginItem(int beginItem) {
        this.beginItem = beginItem;
    }
}
