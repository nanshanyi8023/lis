package cn.hs.publicclass.table.patientinfo;

import java.util.Date;

public class PatientInfo extends PatientInfoKey {
    private String patientName;

    private Date traceLog;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public Date getTraceLog() {
        return traceLog;
    }

    public void setTraceLog(Date traceLog) {
        this.traceLog = traceLog;
    }
}