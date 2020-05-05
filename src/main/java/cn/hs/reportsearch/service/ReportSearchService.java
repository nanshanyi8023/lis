package cn.hs.reportsearch.service;

import cn.hs.publicclass.mapper.CheckApplicationDetailMapper;
import cn.hs.publicclass.mapper.CheckApplicationMapper;
import cn.hs.publicclass.method.FormatDate;
import cn.hs.publicclass.method.GetCookieService;
import cn.hs.publicclass.table.checkapplication.CheckApplication;
import cn.hs.publicclass.table.checkapplicationdetail.CheckApplicationDetail;
import cn.hs.reportsearch.dto.AuditedSampleQueryDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportSearchService {
    @Autowired
    private CheckApplicationMapper checkApplicationMapper;

    @Autowired
    private CheckApplicationDetailMapper checkApplicationDetailMapper;

    @Autowired
    private GetCookieService getCookie;

    public List<CheckApplication> getAuditedSample(AuditedSampleQueryDto auditedSampleQueryDto) {
        if (StringUtils.isNotEmpty(auditedSampleQueryDto.getCheckStartDate())) {
            String checkStartDate = FormatDate.formatstartDate(auditedSampleQueryDto.getCheckStartDate());
            auditedSampleQueryDto.setCheckStartDate(checkStartDate);
        }
        if (StringUtils.isNotEmpty(auditedSampleQueryDto.getCheckEndDate())){
            String checkEndDate = FormatDate.formatEndDay(auditedSampleQueryDto.getCheckEndDate());
            auditedSampleQueryDto.setCheckEndDate(checkEndDate);
        }
        if (StringUtils.isNotEmpty(auditedSampleQueryDto.getAuditStartDate())) {
            String auditStartDate = FormatDate.formatstartDate(auditedSampleQueryDto.getAuditStartDate());
            auditedSampleQueryDto.setAuditStartDate(auditStartDate);
        }
        if (StringUtils.isNotEmpty(auditedSampleQueryDto.getAuditEndDate())){
            String auditEndDate = FormatDate.formatEndDay(auditedSampleQueryDto.getAuditEndDate());
            auditedSampleQueryDto.setAuditEndDate(auditEndDate);
        }
        List<CheckApplication> checkApplicationList = checkApplicationMapper.selectAuditedSample(getCookie.getHosNum(), auditedSampleQueryDto);
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
            if (checkItemGroupName.length() > 0){
                checkApplication.setCheckItemGroup(checkItemGroupName.substring(0, checkItemGroupName.length() - 1));
            }
        }
        return checkApplicationList;

    }
}
