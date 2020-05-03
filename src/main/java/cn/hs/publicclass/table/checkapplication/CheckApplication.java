package cn.hs.publicclass.table.checkapplication;

import java.util.Date;

public class CheckApplication extends CheckApplicationKey {
    //新增检验项目组合字段（用","隔开检验项目组合）
    private String checkItemGroup;

    private String doctorAdviceId;

    private String patientId;

    private String patientName;

    private String collectionContainer;

    private String submitDepartment;

    private String isEmergency;

    private String sampleType;

    private String billingDoctor;

    private Date billingTime;

    private String barcodeNumber;

    private String printStatu;

    private String sampleReceptionStatu;

    private Date sampleReceptionTime;

    private Date sampleReturnTime;

    private String sampleReturnReason;

    private String sampleReturnDoctor;

    private Integer orderNumber;

    private String resultEntryStatu;

    private Date resultEntryTime;

    private String resultEntryDoctor;

    private String resultAuditStatu;

    private Date resultAuditTime;

    private String resultAuditDoctor;

    public String getCheckItemGroup() {
        return checkItemGroup;
    }

    public void setCheckItemGroup(String checkItemGroup) {
        this.checkItemGroup = checkItemGroup;
    }

    public String getDoctorAdviceId() {
        return doctorAdviceId;
    }

    public void setDoctorAdviceId(String doctorAdviceId) {
        this.doctorAdviceId = doctorAdviceId == null ? null : doctorAdviceId.trim();
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? null : patientId.trim();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getCollectionContainer() {
        return collectionContainer;
    }

    public void setCollectionContainer(String collectionContainer) {
        this.collectionContainer = collectionContainer == null ? null : collectionContainer.trim();
    }

    public String getSubmitDepartment() {
        return submitDepartment;
    }

    public void setSubmitDepartment(String submitDepartment) {
        this.submitDepartment = submitDepartment == null ? null : submitDepartment.trim();
    }

    public String getIsEmergency() {
        return isEmergency;
    }

    public void setIsEmergency(String isEmergency) {
        this.isEmergency = isEmergency == null ? null : isEmergency.trim();
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType == null ? null : sampleType.trim();
    }

    public String getBillingDoctor() {
        return billingDoctor;
    }

    public void setBillingDoctor(String billingDoctor) {
        this.billingDoctor = billingDoctor == null ? null : billingDoctor.trim();
    }

    public Date getBillingTime() {
        return billingTime;
    }

    public void setBillingTime(Date billingTime) {
        this.billingTime = billingTime;
    }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public void setBarcodeNumber(String barcodeNumber) {
        this.barcodeNumber = barcodeNumber == null ? null : barcodeNumber.trim();
    }

    public String getPrintStatu() {
        return printStatu;
    }

    public void setPrintStatu(String printStatu) {
        this.printStatu = printStatu == null ? null : printStatu.trim();
    }

    public String getSampleReceptionStatu() {
        return sampleReceptionStatu;
    }

    public void setSampleReceptionStatu(String sampleReceptionStatu) {
        this.sampleReceptionStatu = sampleReceptionStatu == null ? null : sampleReceptionStatu.trim();
    }

    public Date getSampleReceptionTime() {
        return sampleReceptionTime;
    }

    public void setSampleReceptionTime(Date sampleReceptionTime) {
        this.sampleReceptionTime = sampleReceptionTime;
    }

    public Date getSampleReturnTime() {
        return sampleReturnTime;
    }

    public void setSampleReturnTime(Date sampleReturnTime) {
        this.sampleReturnTime = sampleReturnTime;
    }

    public String getSampleReturnReason() {
        return sampleReturnReason;
    }

    public void setSampleReturnReason(String sampleReturnReason) {
        this.sampleReturnReason = sampleReturnReason == null ? null : sampleReturnReason.trim();
    }

    public String getSampleReturnDoctor() {
        return sampleReturnDoctor;
    }

    public void setSampleReturnDoctor(String sampleReturnDoctor) {
        this.sampleReturnDoctor = sampleReturnDoctor == null ? null : sampleReturnDoctor.trim();
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getResultEntryStatu() {
        return resultEntryStatu;
    }

    public void setResultEntryStatu(String resultEntryStatu) {
        this.resultEntryStatu = resultEntryStatu == null ? null : resultEntryStatu.trim();
    }

    public Date getResultEntryTime() {
        return resultEntryTime;
    }

    public void setResultEntryTime(Date resultEntryTime) {
        this.resultEntryTime = resultEntryTime;
    }

    public String getResultEntryDoctor() {
        return resultEntryDoctor;
    }

    public void setResultEntryDoctor(String resultEntryDoctor) {
        this.resultEntryDoctor = resultEntryDoctor == null ? null : resultEntryDoctor.trim();
    }

    public String getResultAuditStatu() {
        return resultAuditStatu;
    }

    public void setResultAuditStatu(String resultAuditStatu) {
        this.resultAuditStatu = resultAuditStatu == null ? null : resultAuditStatu.trim();
    }

    public Date getResultAuditTime() {
        return resultAuditTime;
    }

    public void setResultAuditTime(Date resultAuditTime) {
        this.resultAuditTime = resultAuditTime;
    }

    public String getResultAuditDoctor() {
        return resultAuditDoctor;
    }

    public void setResultAuditDoctor(String resultAuditDoctor) {
        this.resultAuditDoctor = resultAuditDoctor == null ? null : resultAuditDoctor.trim();
    }
}