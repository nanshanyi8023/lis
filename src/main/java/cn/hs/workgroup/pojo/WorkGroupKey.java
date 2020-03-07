package cn.hs.workgroup.pojo;

public class WorkGroupKey {
    private String hosnum;

    private String workGroupId;

    public String getHosnum() {
        return hosnum;
    }

    public void setHosnum(String hosnum) {
        this.hosnum = hosnum == null ? null : hosnum.trim();
    }

    public String getWorkGroupId() {
        return workGroupId;
    }

    public void setWorkGroupId(String workGroupId) {
        this.workGroupId = workGroupId == null ? null : workGroupId.trim();
    }
}