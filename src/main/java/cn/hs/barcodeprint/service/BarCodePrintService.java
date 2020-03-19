package cn.hs.barcodeprint.service;

import cn.hs.barcodeprint.dto.CheckApplicationSearchDto;
import cn.hs.barcodeprint.dto.PatientSearchDto;
import cn.hs.publicclass.mapper.CheckApplicationDetailMapper;
import cn.hs.publicclass.mapper.CheckApplicationMapper;
import cn.hs.publicclass.mapper.PatientInfoMapper;
import cn.hs.publicclass.table.checkapplication.CheckApplication;
import cn.hs.publicclass.table.checkapplicationdetail.CheckApplicationDetail;
import cn.hs.publicclass.table.patientinfo.PatientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BarCodePrintService {
    @Autowired
    private PatientInfoMapper patientInfoMapper;

    @Autowired
    private CheckApplicationMapper checkApplicationMapper;

    @Autowired
    private CheckApplicationDetailMapper checkApplicationDetailMapper;

    @Autowired
    private HttpServletRequest request;

    //查找医院号
    private String getHosNum() {
        Cookie cookies[] = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("hosNum")) {
                return cookie.getValue();
            }
        }
        return null;
    }

    //查询符合条件的患者信息
    public List<PatientInfo> getPatientInfo(PatientSearchDto patientSearchDto) {
        return patientInfoMapper.getPatientInfo(this.getHosNum(), patientSearchDto);
    }

    //查询符合条件的检验申请
    public List<CheckApplication> getCheckApplication(CheckApplicationSearchDto checkApplicationSearchDto) {
        List<String> patientIdList = checkApplicationSearchDto.getPatientIdList();
        Date startDate = null;
        Date endDate = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String stringStartDate = checkApplicationSearchDto.getStartDate() + " 00:00:00";
            String stringEndDate = checkApplicationSearchDto.getEndDate() + " 23:59:59";
            if (stringStartDate != null && stringStartDate != ""){
                startDate = simpleDateFormat.parse(stringStartDate);
            }
            if (stringEndDate != null && stringEndDate != ""){
                endDate = simpleDateFormat.parse(stringEndDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<CheckApplication> checkApplicationList = checkApplicationMapper.selectByPatientAndTime(this.getHosNum(), patientIdList, startDate, endDate);

        //将对应的检验项目组合拼到checkApplicationList中
        //需要的所有检验申请id
        List<String> checkApplicationIdList = new ArrayList<String>();
        for (CheckApplication checkApplication : checkApplicationList) {
            checkApplicationIdList.add(checkApplication.getItemId());
        }
        //查询所有需要的检验申请id对应的检验项目组合
        List<CheckApplicationDetail> checkApplicationDetailList = checkApplicationDetailMapper.getCheckItemGroup(this.getHosNum(), checkApplicationIdList);
        //将对应的检验项目组合赋值给checkApplicationList
        for (CheckApplication checkApplication : checkApplicationList) {
            String checkItemGroupName = "";
            for (CheckApplicationDetail checkApplicationDetail : checkApplicationDetailList) {
                if (checkApplication.getItemId().equals(checkApplicationDetail.getCheckApplicationId())) {
                    checkItemGroupName = checkItemGroupName + checkApplicationDetail.getCheckItemGroupName() + ",";
                }
            }
            checkApplication.setCheckItemGroup(checkItemGroupName.substring(0, checkItemGroupName.length() - 1));
        }
        return checkApplicationList;
    }

    //查询打印条码
    public Object getPrintBarCode(List<String> checkApplicationIdList) {
        List<CheckApplication> barcodeNnumberList = new ArrayList<CheckApplication>();
        for (int i = 0; i < checkApplicationIdList.size(); i++) {
            CheckApplication item = new CheckApplication();
            item.setItemId(checkApplicationIdList.get(i));
            String barcodeNnumber = checkApplicationMapper.selectBarcodeNnumber(this.getHosNum(), checkApplicationIdList.get(i));
            if (barcodeNnumber == null || barcodeNnumber.equals("")) {     //不存在条码，生成
                barcodeNnumber = checkApplicationMapper.getMaxBarcodeNnumber(this.getHosNum());
                item.setBarcodeNumber(barcodeNnumber);
            } else {  //存在条码，直接返回
                item.setBarcodeNumber(barcodeNnumber);
                barcodeNnumberList.add(item);
            }
            barcodeNnumberList.add(item);
        }
        return barcodeNnumberList;
    }
}
