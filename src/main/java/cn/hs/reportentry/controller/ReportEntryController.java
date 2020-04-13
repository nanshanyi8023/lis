package cn.hs.reportentry.controller;

import cn.hs.publicclass.method.ApiResult;
import cn.hs.reportentry.service.ReportEntryService;
import cn.hs.samplerecept.dto.ReceptedSampleQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reportEntry")
public class ReportEntryController {
    @Autowired
    private ReportEntryService reportEntryService;

//    //查询符合条件的已接收样本
//    @RequestMapping(value = "/getReceptedSample.json", method = RequestMethod.POST)
//    public ApiResult getReceptedSample(@RequestBody ReceptedSampleQueryDto sampleQueryDto){
//        try {
//            return ApiResult.success(reportEntryService.getReceptedSample(sampleQueryDto));
//        } catch (Exception e){
//            return ApiResult.failed(e.getMessage());
//        }
//    }
}
