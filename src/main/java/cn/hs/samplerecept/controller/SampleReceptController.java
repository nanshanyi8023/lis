package cn.hs.samplerecept.controller;

import cn.hs.publicclass.method.ApiResult;
import cn.hs.samplerecept.dto.ReceptedSampleQueryDto;
import cn.hs.samplerecept.service.SampleReceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sampleRecept")
public class SampleReceptController {
    @Autowired
    private SampleReceptService sampleReceptService;

    //查询符合条件的已接收样本
    @RequestMapping(value = "/getReceptedSample.json", method = RequestMethod.POST)
    public ApiResult getReceptedSample(@RequestBody ReceptedSampleQueryDto receptedSampleQueryDto){
        try {
            return ApiResult.success(sampleReceptService.getReceptedSample(receptedSampleQueryDto));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //根据条码号接收样本
    @RequestMapping(value = "/receiveSample.json", method = RequestMethod.GET)
    public ApiResult receiveSample(@RequestParam(required = true) String barCodeNumber){
        try {
            sampleReceptService.receiveSample(barCodeNumber);
            return ApiResult.success();
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }
}
