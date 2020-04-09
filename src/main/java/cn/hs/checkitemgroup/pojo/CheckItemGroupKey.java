package cn.hs.checkitemgroup.pojo;

public class CheckItemGroupKey {
    private String hosnum;

    private String groupId;

    public String getHosnum() {
        return hosnum;
    }

    public void setHosnum(String hosnum) {
        this.hosnum = hosnum == null ? null : hosnum.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }
}