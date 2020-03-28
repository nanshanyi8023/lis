package cn.hs.samplerecept.service;

import cn.hs.publicclass.mapper.CheckApplicationDetailMapper;
import cn.hs.publicclass.mapper.CheckApplicationMapper;
import cn.hs.publicclass.method.FormatDate;
import cn.hs.publicclass.table.checkapplication.CheckApplication;
import cn.hs.publicclass.table.checkapplicationdetail.CheckApplicationDetail;
import cn.hs.samplerecept.dto.ReceptedSampleQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
        String startDate = FormatDate.formatstartDate(receptedSampleQueryDto.getStartDate());
        receptedSampleQueryDto.setStartDate(startDate);
        String endDate = FormatDate.formatEndDay(receptedSampleQueryDto.getEndDate());
        receptedSampleQueryDto.setEndDate(endDate);
        List<CheckApplication> checkApplicationList = checkApplicationMapper.selectReceptedSample(this.getHosNum(), receptedSampleQueryDto);
        if (checkApplicationList.size() <= 0) {
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
    public String receiveSample(String barCodeNumber) {
        //判断样本条码号是否存在
        CheckApplication checkApplication = checkApplicationMapper.getCheckApplication(this.getHosNum(), barCodeNumber);
        if (checkApplication == null) {
            return "未查询到该条码号，请检查输入条码号是否正确";
        }
        //判断条码号对应的样本是否已接收
        checkApplication = checkApplicationMapper.getReceptionStatu(this.getHosNum(), barCodeNumber);
        if (checkApplication.getSampleReceptionStatu().equals("已接收")) {
            String receptionDay = FormatDate.formatDay(checkApplication.getSampleReceptionTime());
            return "该样本已于" + receptionDay + "接收!";
        } else if (checkApplication.getSampleReceptionStatu().equals("已退回")) {
            String returnDay = FormatDate.formatDay(checkApplication.getSampleReturnTime());
            return "该样本已于" + returnDay + "退回!";
        }
        //接收样本
        checkApplicationMapper.receiveSample(this.getHosNum(), barCodeNumber);
        return "接收样本成功";
    }

    public int returnSample(List<String> sampleIdList) {
        if (sampleIdList.isEmpty()){
            return 0;
        }
        return checkApplicationMapper.returnSample(this.getHosNum(),sampleIdList);
    }
}
