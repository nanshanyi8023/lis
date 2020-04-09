package cn.hs.barcodeprint.service;

import cn.hs.barcodeprint.dto.CheckApplicationSearchDto;
import cn.hs.barcodeprint.dto.PatientSearchDto;
import cn.hs.publicclass.mapper.CheckApplicationDetailMapper;
import cn.hs.publicclass.mapper.CheckApplicationMapper;
import cn.hs.publicclass.mapper.PatientInfoMapper;
import cn.hs.publicclass.method.FormatDate;
import cn.hs.publicclass.method.GetCookieService;
import cn.hs.publicclass.table.checkapplication.CheckApplication;
import cn.hs.publicclass.table.checkapplication.CheckApplicationKey;
import cn.hs.publicclass.table.checkapplicationdetail.CheckApplicationDetail;
import cn.hs.publicclass.table.patientinfo.PatientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
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
    private GetCookieService getCookie;

    //查询符合条件的患者信息
    public List<PatientInfo> getPatientInfo(PatientSearchDto patientSearchDto) {
        if (StringUtils.isNotEmpty(patientSearchDto.getStartDate())){
            String startDate = FormatDate.formatstartDate(patientSearchDto.getStartDate());
            patientSearchDto.setStartDate(startDate);
        }
        if (StringUtils.isNotEmpty(patientSearchDto.getEndDate())){
            String endDate = FormatDate.formatEndDay(patientSearchDto.getEndDate());
            patientSearchDto.setEndDate(endDate);
        }
        return patientInfoMapper.getPatientInfo(getCookie.getHosNum(), patientSearchDto);
    }

    //查询符合条件的检验申请
    public List<CheckApplication> getCheckApplication(CheckApplicationSearchDto checkApplicationSearchDto) {
        if (StringUtils.isNotEmpty(checkApplicationSearchDto.getStartDate())){
            String startDate = FormatDate.formatstartDate(checkApplicationSearchDto.getStartDate());
            checkApplicationSearchDto.setStartDate(startDate);
        }
        if (StringUtils.isNotEmpty(checkApplicationSearchDto.getEndDate())){
            String endDate = FormatDate.formatEndDay(checkApplicationSearchDto.getEndDate());
            checkApplicationSearchDto.setEndDate(endDate);
        }
        List<CheckApplication> checkApplicationList = checkApplicationMapper.selectByPatientAndTime(getCookie.getHosNum(), checkApplicationSearchDto);

        //将对应的检验项目组合拼到checkApplicationList中
        //需要的所有检验申请id
        List<String> checkApplicationIdList = new ArrayList<String>();
        for (CheckApplication checkApplication : checkApplicationList) {
            checkApplicationIdList.add(checkApplication.getItemId());
        }
        if (checkApplicationIdList.size() <= 0) {
            return checkApplicationList;
        }
        //查询所有需要的检验申请id对应的检验项目组合
        List<CheckApplicationDetail> checkApplicationDetailList = checkApplicationDetailMapper.getCheckItemGroup(getCookie.getHosNum(), checkApplicationIdList);
        //将对应的检验项目组合赋值给checkApplicationList
        for (CheckApplication checkApplication : checkApplicationList) {
            String checkItemGroupName = "";
            for (CheckApplicationDetail checkApplicationDetail : checkApplicationDetailList) {
                if (checkApplication.getItemId().equals(checkApplicationDetail.getCheckApplicationId())) {
                    checkItemGroupName = checkItemGroupName + checkApplicationDetail.getCheckItemGroupName() + ",";
                }
            }
            if (checkItemGroupName.length() > 0){
                checkApplication.setCheckItemGroup(checkItemGroupName.substring(0, checkItemGroupName.length() - 1));
            }
        }
        return checkApplicationList;
    }

    //查询打印条码
    public Object getPrintBarCode(List<String> checkApplicationIdList) {
        List<CheckApplication> barcodeNnumberList = new ArrayList<CheckApplication>();
        for (int i = 0; i < checkApplicationIdList.size(); i++) {
            //根据id查找对应的检验申请信息
            CheckApplicationKey checkApplicationKey = new CheckApplicationKey();
            checkApplicationKey.setHosnum(getCookie.getHosNum());
            checkApplicationKey.setItemId(checkApplicationIdList.get(i));
            CheckApplication item = checkApplicationMapper.selectByPrimaryKey(checkApplicationKey);
            //查询是否存在对应的条码号
            String barcodeNnumber = checkApplicationMapper.selectBarcodeNnumber(getCookie.getHosNum(), checkApplicationIdList.get(i));
            if (StringUtils.isEmpty(barcodeNnumber)) {     //不存在条码，生成
                barcodeNnumber = checkApplicationMapper.getMaxBarcodeNnumber(getCookie.getHosNum());
                item.setBarcodeNumber(barcodeNnumber);
                //向数据库中插入该检验申请的条码号
                checkApplicationMapper.updateBarcodeNnumber(getCookie.getHosNum(), checkApplicationIdList.get(i), barcodeNnumber);
            } else {  //存在条码，直接返回
                item.setBarcodeNumber(barcodeNnumber);
            }
            barcodeNnumberList.add(item);
        }
        return barcodeNnumberList;
    }
}
