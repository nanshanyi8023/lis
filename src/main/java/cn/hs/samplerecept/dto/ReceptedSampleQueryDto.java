package cn.hs.samplerecept.dto;

public class ReceptedSampleQueryDto {
    private String barCodeNumber;      //条码号
    private String startDate;             //开始时间
    private String endDate;               //结束时间

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
}
