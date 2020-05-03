package cn.hs.reportentry.dto;

import java.util.List;

public class SampleDetailedInfoDto {
    private String itemId;
    private String isEmergency;
    private String barcodeNumber;
    private String patientName;
    private String patientId;
    private String submitDepartment;
    private String sampleType;
    private String sampleReceptionTime;
    private String resultEntryStatu;
    private String resultEntryTime;
    private String resultEntryDoctor;
    private String resultAuditStatu;
    private String resultAuditTime;
    private String resultAuditDoctor;
    private String sex;
    private String age;
    private List<String> checkItemGroupNameList;
    private List<String> equipmentList;

    public List<String> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<String> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public List<String> getCheckItemGroupNameList() {
        return checkItemGroupNameList;
    }

    public void setCheckItemGroupNameList(List<String> checkItemGroupNameList) {
        this.checkItemGroupNameList = checkItemGroupNameList;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getIsEmergency() {
        return isEmergency;
    }

    public void setIsEmergency(String isEmergency) {
        this.isEmergency = isEmergency;
    }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public void setBarcodeNumber(String barcodeNumber) {
        this.barcodeNumber = barcodeNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getSubmitDepartment() {
        return submitDepartment;
    }

    public void setSubmitDepartment(String submitDepartment) {
        this.submitDepartment = submitDepartment;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public String getSampleReceptionTime() {
        return sampleReceptionTime;
    }

    public void setSampleReceptionTime(String sampleReceptionTime) {
        this.sampleReceptionTime = sampleReceptionTime;
    }

    public String getResultEntryStatu() {
        return resultEntryStatu;
    }

    public void setResultEntryStatu(String resultEntryStatu) {
        this.resultEntryStatu = resultEntryStatu;
    }

    public String getResultEntryTime() {
        return resultEntryTime;
    }

    public void setResultEntryTime(String resultEntryTime) {
        this.resultEntryTime = resultEntryTime;
    }

    public String getResultEntryDoctor() {
        return resultEntryDoctor;
    }

    public void setResultEntryDoctor(String resultEntryDoctor) {
        this.resultEntryDoctor = resultEntryDoctor;
    }

    public String getResultAuditStatu() {
        return resultAuditStatu;
    }

    public void setResultAuditStatu(String resultAuditStatu) {
        this.resultAuditStatu = resultAuditStatu;
    }

    public String getResultAuditTime() {
        return resultAuditTime;
    }

    public void setResultAuditTime(String resultAuditTime) {
        this.resultAuditTime = resultAuditTime;
    }

    public String getResultAuditDoctor() {
        return resultAuditDoctor;
    }

    public void setResultAuditDoctor(String resultAuditDoctor) {
        this.resultAuditDoctor = resultAuditDoctor;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
