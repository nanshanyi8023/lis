package cn.hs.barcodeprint.service;

import cn.hs.barcodeprint.dto.CheckApplicationSearchDto;
import cn.hs.barcodeprint.dto.PatientSearchDto;
import cn.hs.publicclass.mapper.CheckApplicationFormMapper;
import cn.hs.publicclass.mapper.PatientInfoMapper;
import cn.hs.publicclass.table.checkapplicationform.CheckApplicationForm;
import cn.hs.publicclass.table.patientinfo.PatientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class BarCodePrintService {
    @Autowired
    private PatientInfoMapper patientInfoMapper;

    @Autowired
    private CheckApplicationFormMapper checkApplicationFormMapper;

    @Autowired
    private HttpServletRequest request;

    //查找医院号
    private String getHosNum(){
        Cookie cookies[]=request.getCookies();
        for(Cookie cookie:cookies){
            if (cookie.getName().equals("hosNum")){
                return cookie.getValue();
            }
        }
        return null;
    }

    //查询符合条件的患者信息
    public List<PatientInfo> getPatientInfo(PatientSearchDto patientSearchDto) {
        List<PatientInfo> patientInfoList = patientInfoMapper.getPatientInfo(this.getHosNum(), patientSearchDto);
        return patientInfoList;
    }

    public List<CheckApplicationForm> getCheckApplication(CheckApplicationSearchDto checkApplicationSearchDto) {
        List<CheckApplicationForm> checkApplicationFormList = checkApplicationFormMapper.selectByPatientAndTime(this.getHosNum(), checkApplicationSearchDto);
        return checkApplicationFormList;
    }
}
