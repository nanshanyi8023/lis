package cn.hs.reportentry.service;

import cn.hs.checkitem.mapper.CheckItemMapper;
import cn.hs.checkitem.pojo.CheckItem;
import cn.hs.equipment.mapper.EquipmentMapper;
import cn.hs.publicclass.mapper.CheckApplicationDetailMapper;
import cn.hs.publicclass.mapper.CheckApplicationMapper;
import cn.hs.publicclass.mapper.CheckResultMapper;
import cn.hs.publicclass.method.GetCookieService;
import cn.hs.publicclass.table.checkresult.CheckResult;
import cn.hs.reportentry.dto.SampleDetailedInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportEntryService {

    @Autowired
    private CheckApplicationMapper checkApplicationMapper;

    @Autowired
    private CheckApplicationDetailMapper checkApplicationDetailMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private CheckItemMapper checkItemMapper;

    @Autowired
    private CheckResultMapper checkResultMapper;

    @Autowired
    private GetCookieService getCookie;

    //根据检验申请Id查询样本详细信息
    public SampleDetailedInfoDto getSampleDetailedInfo(String checkApplicationId) {
        SampleDetailedInfoDto sampleDetailedInfoDto = checkApplicationMapper.getSampleDetailedInfo(getCookie.getHosNum(),checkApplicationId);
        //将对应的检验项目组合添加到sampleDetailedInfoDto中
        List<String> checkItemGroupNameList = checkApplicationDetailMapper.getCheckItemGroupByApplicationId(getCookie.getHosNum(),sampleDetailedInfoDto.getItemId());
        sampleDetailedInfoDto.setCheckItemGroupNameList(checkItemGroupNameList);
        //将对应的检验仪器添加到sampleDetailedInfoDto中
        List<String> equipmentList = equipmentMapper.getEquipmentByBarCodeNumber(getCookie.getHosNum(), checkApplicationId);
        sampleDetailedInfoDto.setEquipmentList(equipmentList);
        return sampleDetailedInfoDto;
    }

    //根据检验申请Id查找对应的检验项目及默认结果
    public List<CheckItem> getCheckItemAndDefaultValue(String checkApplicationId) {
        String resultEntryStatu = checkApplicationMapper.getEntryStatu(getCookie.getHosNum(),checkApplicationId);
        List<CheckItem> checkItemList = checkItemMapper.getCheckItemAndDefaultValue(getCookie.getHosNum(), checkApplicationId);
        if (resultEntryStatu.equals("未录入") || resultEntryStatu.equals("")){
            return checkItemList;
        }else if (resultEntryStatu.equals("已录入")){
            List<CheckResult> checkResultList = checkResultMapper.getCheckResult(getCookie.getHosNum(), checkApplicationId);
            for (CheckItem checkItem : checkItemList){
                for (CheckResult checkResult : checkResultList){
                    if (checkItem.getItemId().equals(checkResult.getCheckItemId())){
                        checkItem.setDefaultValue(checkResult.getResult());
                        break;
                    }
                }
            }
            return checkItemList;
        }
        return null;
    }

    //保存检验结果
    public void saveCheckResult(List<CheckResult> checkResultList) {
        String checkApplicationId = checkResultList.get(0).getCheckApplicationId();
        String entryStatu = checkApplicationMapper.getEntryStatu(getCookie.getHosNum(), checkApplicationId);
        if (entryStatu.equals("未录入") || entryStatu.equals("")){
            for(CheckResult checkResult : checkResultList){
                checkResult.setHosnum(getCookie.getHosNum());
                checkResultMapper.insertSelective(checkResult);
            }
        }
        if (entryStatu.equals("已录入")){
            for(CheckResult checkResult : checkResultList){
                checkResult.setHosnum(getCookie.getHosNum());
                checkResultMapper.updateByPrimaryKeySelective(checkResult);
            }
        }
        checkApplicationMapper.changeEntry(getCookie.getHosNum(),getCookie.getLoginName(),checkApplicationId);
    }

    //审核检验结果
    public void auditCheckResult(String checkApplicationId) {
        checkApplicationMapper.changeAudit(getCookie.getHosNum(),getCookie.getLoginName(),checkApplicationId);
    }

    //取消审核检验结果
    public void cancelAuditCheckResult(String checkApplicationId) {
        checkApplicationMapper.changeCancelAudit(getCookie.getHosNum(),checkApplicationId);
    }
}
