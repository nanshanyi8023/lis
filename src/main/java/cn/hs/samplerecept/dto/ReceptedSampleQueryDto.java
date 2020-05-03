package cn.hs.samplerecept.dto;

public class ReceptedSampleQueryDto {
    private String barCodeNumber;      //条码号
    private String startDate;             //开始时间
    private String endDate;               //结束时间
    private String resultEntryStatu;      //录入状态
    private String resultAuditStatu;      //审核状态

    public String getBarCodeNumber() {
        return barCodeNumber;
    }

    public void setBarCodeNumber(String barCodeNumber) {
        this.barCodeNumber = barCodeNumber;
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

    public String getResultEntryStatu() {
        return resultEntryStatu;
    }

    public void setResultEntryStatu(String resultEntryStatu) {
        this.resultEntryStatu = resultEntryStatu;
    }

    public String getResultAuditStatu() {
        return resultAuditStatu;
    }

    public void setResultAuditStatu(String resultAuditStatu) {
        this.resultAuditStatu = resultAuditStatu;
    }
}
