package cn.hs.reportsearch.dto;

public class AuditedSampleQueryDto {
    private String patientId;
    private String patientName;
    private String barCodeNumber;
    private String checkStartDate;
    private String checkEndDate;
    private String auditStartDate;
    private String auditEndDate;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getBarCodeNumber() {
        return barCodeNumber;
    }

    public void setBarCodeNumber(String barCodeNumber) {
        this.barCodeNumber = barCodeNumber;
    }

    public String getCheckStartDate() {
        return checkStartDate;
    }

    public void setCheckStartDate(String checkStartDate) {
        this.checkStartDate = checkStartDate;
    }

    public String getCheckEndDate() {
        return checkEndDate;
    }

    public void setCheckEndDate(String checkEndDate) {
        this.checkEndDate = checkEndDate;
    }

    public String getAuditStartDate() {
        return auditStartDate;
    }

    public void setAuditStartDate(String auditStartDate) {
        this.auditStartDate = auditStartDate;
    }

    public String getAuditEndDate() {
        return auditEndDate;
    }

    public void setAuditEndDate(String auditEndDate) {
        this.auditEndDate = auditEndDate;
    }
}

