package cn.hs.samplerecept.dto;

import java.util.List;

public class RetrunSampleDto {
    private List<String> sampleIdList;
    private String returnReason;

    public List<String> getSampleIdList() {
        return sampleIdList;
    }

    public void setSampleIdList(List<String> sampleIdList) {
        this.sampleIdList = sampleIdList;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }
}
