package cn.hs.samplerecept.dto;

import java.util.List;

public class RetrunSampleDto {
    private List<String> sampleIdList;
    private String returnReason;
    private String returnDoctor;

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

    public String getReturnDoctor() {
        return returnDoctor;
    }

    public void setReturnDoctor(String returnDoctor) {
        this.returnDoctor = returnDoctor;
    }
}
