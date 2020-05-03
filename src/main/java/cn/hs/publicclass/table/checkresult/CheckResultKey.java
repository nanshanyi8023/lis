package cn.hs.publicclass.table.checkresult;

public class CheckResultKey {
    private String hosnum;

    private String checkApplicationId;

    private String checkItemId;

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

    public String getCheckItemId() {
        return checkItemId;
    }

    public void setCheckItemId(String checkItemId) {
        this.checkItemId = checkItemId == null ? null : checkItemId.trim();
    }
}