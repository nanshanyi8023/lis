package cn.hs.samplerecept.service;

import cn.hs.publicclass.mapper.CheckApplicationDetailMapper;
import cn.hs.publicclass.mapper.CheckApplicationMapper;
import cn.hs.publicclass.table.checkapplication.CheckApplication;
import cn.hs.publicclass.table.checkapplicationdetail.CheckApplicationDetail;
import cn.hs.samplerecept.dto.ReceptedSampleQueryDto;
import org.apache.commons.lang.StringUtils;
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
public class SampleReceptService {

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


    //查询符合条件的已接收样本
    public List<CheckApplication> getReceptedSample(ReceptedSampleQueryDto receptedSampleQueryDto) {
        Date startDate = null;
        Date endDate = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String stringStartDate = receptedSampleQueryDto.getStartDate() + " 00:00:00";
            String stringEndDate = receptedSampleQueryDto.getEndDate() + " 23:59:59";
            if (StringUtils.isNotEmpty(stringStartDate)) {
                startDate = simpleDateFormat.parse(stringStartDate);
            }
            if (StringUtils.isNotEmpty(stringEndDate)) {
                endDate = simpleDateFormat.parse(stringEndDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<CheckApplication> checkApplicationList = checkApplicationMapper.selectReceptedSample(this.getHosNum(), receptedSampleQueryDto.getBarCodeNumber(), startDate, endDate);
        if (checkApplicationList.size() <= 0){
            return checkApplicationList;
        }

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

    //根据条码号接收样本
    public void receiveSample(String barCodeNumber) {
        checkApplicationMapper.receiveSample(this.getHosNum(),barCodeNumber);
    }
}
