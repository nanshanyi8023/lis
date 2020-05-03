package cn.hs.reportentry.controller;

import cn.hs.publicclass.method.ApiResult;
import cn.hs.publicclass.table.checkresult.CheckResult;
import cn.hs.reportentry.service.ReportEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportEntry")
public class ReportEntryController {
    @Autowired
    private ReportEntryService reportEntryService;

    //根据检验申请Id查询样本详细信息
    @RequestMapping(value = "/getSampleDetailedInfo.json", method = RequestMethod.GET)
    public ApiResult getSampleDetailedInfo(@RequestParam(required = true) String checkApplicationId){
        try {
            return ApiResult.success(reportEntryService.getSampleDetailedInfo(checkApplicationId));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //根据检验申请Id查找对应的检验项目及默认结果
    @RequestMapping(value = "/getCheckItemAndDefaultValue.json", method = RequestMethod.GET)
    public ApiResult getCheckItemAndDefaultValue(@RequestParam(required = true) String checkApplicationId){
        try {
            return ApiResult.success(reportEntryService.getCheckItemAndDefaultValue(checkApplicationId));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //保存检验结果
    @RequestMapping(value = "/saveCheckResult.json", method = RequestMethod.POST)
    public ApiResult saveCheckResult(@RequestBody(required = true) List<CheckResult> checkResultList){
        try {
            reportEntryService.saveCheckResult(checkResultList);
            return ApiResult.success();
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //审核检验结果
    @RequestMapping(value = "/auditCheckResult.json", method = RequestMethod.GET)
    public ApiResult auditCheckResult(@RequestParam(required = true) String checkApplicationId){
        try {
            reportEntryService.auditCheckResult(checkApplicationId);
            return ApiResult.success();
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //取消审核检验结果
    @RequestMapping(value = "/cancelAuditCheckResult.json", method = RequestMethod.GET)
    public ApiResult cancelAuditCheckResult(@RequestParam(required = true) String checkApplicationId){
        try {
            reportEntryService.cancelAuditCheckResult(checkApplicationId);
            return ApiResult.success();
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }
}
