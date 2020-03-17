package cn.hs.publicclass.table.checkapplicationform;

import java.math.BigDecimal;
import java.util.Date;

public class CheckApplicationForm extends CheckApplicationFormKey {
    private String checkItemGroupId;

    private String checkItemGroupName;

    private String patientId;

    private String patientName;

    private String collectionContainer;

    private BigDecimal itemPrice;

    private String submitDepartment;

    private String isEmergency;

    private String billingDoctor;

    private Date billingTime;

    private String printStatu;

    private String sampleReceptionStatu;

    private String barcodeNumber;

    private Integer orderNumber;

    public String getCheckItemGroupId() {
        return checkItemGroupId;
    }

    public void setCheckItemGroupId(String checkItemGroupId) {
        this.checkItemGroupId = checkItemGroupId == null ? null : checkItemGroupId.trim();
    }

    public String getCheckItemGroupName() {
        return checkItemGroupName;
    }

    public void setCheckItemGroupName(String checkItemGroupName) {
        this.checkItemGroupName = checkItemGroupName == null ? null : checkItemGroupName.trim();
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

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
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

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public void setBarcodeNumber(String barcodeNumber) {
        this.barcodeNumber = barcodeNumber == null ? null : barcodeNumber.trim();
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}