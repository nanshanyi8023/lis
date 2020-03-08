package cn.hs.workgroup.pojo;

public class WorkGroupDetailKey {
    private String hosnum;

    private String workGroupId;

    private String itemId;

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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }
}