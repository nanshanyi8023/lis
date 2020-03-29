package cn.hs.samplereturn.service;

import cn.hs.publicclass.mapper.CheckApplicationDetailMapper;
import cn.hs.publicclass.mapper.CheckApplicationMapper;
import cn.hs.publicclass.method.FormatDate;
import cn.hs.publicclass.method.GetCookieService;
import cn.hs.publicclass.table.checkapplication.CheckApplication;
import cn.hs.publicclass.table.checkapplicationdetail.CheckApplicationDetail;
import cn.hs.samplerecept.dto.ReceptedSampleQueryDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SampleReturnService {

    @Autowired
    private CheckApplicationMapper checkApplicationMapper;

    @Autowired
    private CheckApplicationDetailMapper checkApplicationDetailMapper;

    @Autowired
    private GetCookieService getCookie;

    //查找已退回的样本
    public List<CheckApplication> getReturnedSample(ReceptedSampleQueryDto returnSampleQueryDto) {
        if (StringUtils.isNotEmpty(returnSampleQueryDto.getStartDate())) {
            String startDate = FormatDate.formatstartDate(returnSampleQueryDto.getStartDate());
            returnSampleQueryDto.setStartDate(startDate);
        }
        if (StringUtils.isNotEmpty(returnSampleQueryDto.getEndDate())){
            String endDate = FormatDate.formatEndDay(returnSampleQueryDto.getEndDate());
            returnSampleQueryDto.setEndDate(endDate);
        }
        List<CheckApplication> checkApplicationList = checkApplicationMapper.selectReturnedSample(getCookie.getHosNum(), returnSampleQueryDto);
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
        List<CheckApplicationDetail> checkApplicationDetailList = checkApplicationDetailMapper.getCheckItemGroup(getCookie.getHosNum(), checkApplicationIdList);
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
}
