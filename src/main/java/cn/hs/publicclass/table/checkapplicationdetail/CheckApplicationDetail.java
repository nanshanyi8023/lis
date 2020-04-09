package cn.hs.publicclass.table.checkapplicationdetail;

public class CheckApplicationDetail extends CheckApplicationDetailKey {
    private String checkItemGroupName;

    public String getCheckItemGroupName() {
        return checkItemGroupName;
    }

    public void setCheckItemGroupName(String checkItemGroupName) {
        this.checkItemGroupName = checkItemGroupName == null ? null : checkItemGroupName.trim();
    }
}