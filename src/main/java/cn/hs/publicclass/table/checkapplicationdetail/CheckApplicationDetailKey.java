package cn.hs.publicclass.table.checkapplicationdetail;

public class CheckApplicationDetailKey {
    private String hosnum;

    private String checkApplicationId;

    private String checkItemGroupId;

    public String getHosnum() {
        return hosnum;
    }

    public void setHosnum(String hosnum) {
        this.hosnum = hosnum == null ? null : hosnum.trim();
    }

    public String getCheckApplicationId() {
        return checkApplicationId;
    }

    public void setCheckApplicationId(String checkApplicationId) {
        this.checkApplicationId = checkApplicationId == null ? null : checkApplicationId.trim();
    }

    public String getCheckItemGroupId() {
        return checkItemGroupId;
    }

    public void setCheckItemGroupId(String checkItemGroupId) {
        this.checkItemGroupId = checkItemGroupId == null ? null : checkItemGroupId.trim();
    }
}