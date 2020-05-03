package cn.hs.publicclass.table.checkresult;

import java.util.Date;

public class CheckResult extends CheckResultKey {
    private String result;

    private Date traceLog;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public Date getTraceLog() {
        return traceLog;
    }

    public void setTraceLog(Date traceLog) {
        this.traceLog = traceLog;
    }
}