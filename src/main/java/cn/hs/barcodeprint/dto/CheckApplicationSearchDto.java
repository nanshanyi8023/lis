package cn.hs.barcodeprint.dto;

import java.util.List;

public class CheckApplicationSearchDto {
    private List<String> patientIdList;   //患者id
    private String startDate;             //开始时间
    private String endDate;               //结束时间

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
}
