package cn.hs.samplereturn.controller;

import cn.hs.publicclass.method.ApiResult;
import cn.hs.samplerecept.dto.ReceptedSampleQueryDto;
import cn.hs.samplereturn.service.SampleReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sampleReturn")
public class SampleReturnController {
    @Autowired
    private SampleReturnService sampleReturnService;

    //查找已退回的样本
    @RequestMapping(value = "/getReturnedSample.json", method = RequestMethod.POST)
    public ApiResult getReturnedSample(@RequestBody ReceptedSampleQueryDto returnSampleQueryDto) {
        try {
            return ApiResult.success(sampleReturnService.getReturnedSample(returnSampleQueryDto));
        } catch (Exception e) {
            return ApiResult.failed(e.getMessage());
        }
    }
}
