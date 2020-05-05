package cn.hs.reportsearch.controller;

import cn.hs.publicclass.method.ApiResult;
import cn.hs.reportsearch.dto.AuditedSampleQueryDto;
import cn.hs.reportsearch.service.ReportSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reportSearch")
public class ReportSearchController {
    @Autowired
    private ReportSearchService reportSearchService;

    //查询符合条件的已审核样本
    @RequestMapping(value = "/getAuditedSample.json", method = RequestMethod.POST)
    public ApiResult getAuditedSample(@RequestBody AuditedSampleQueryDto auditedSampleQueryDto) {
        try {
            return ApiResult.success(reportSearchService.getAuditedSample(auditedSampleQueryDto));
        } catch (Exception e) {
            return ApiResult.failed(e.getMessage());
        }
    }

}
