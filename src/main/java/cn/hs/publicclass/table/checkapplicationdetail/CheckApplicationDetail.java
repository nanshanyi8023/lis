package cn.hs.publicclass.table.checkapplicationdetail;

import java.util.Date;

public class CheckApplicationDetail extends CheckApplicationDetailKey {
    //添加检验项目组合名称字段
    private String checkItemGroupName;

    private Date traceLog;

    public String getCheckItemGroupName() {
        return checkItemGroupName;
    }

    public void setCheckItemGroupName(String checkItemGroupName) {
        this.checkItemGroupName = checkItemGroupName;
    }

    public Date getTraceLog() {
        return traceLog;
    }

    public void setTraceLog(Date traceLog) {
        this.traceLog = traceLog;
    }
}