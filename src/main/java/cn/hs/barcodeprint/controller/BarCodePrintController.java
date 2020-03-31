package cn.hs.barcodeprint.controller;

import cn.hs.barcodeprint.dto.CheckApplicationSearchDto;
import cn.hs.barcodeprint.dto.PatientSearchDto;
import cn.hs.barcodeprint.service.BarCodePrintService;
import cn.hs.publicclass.method.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barCodePrint")
public class BarCodePrintController {

    @Autowired
    private BarCodePrintService barCodePrintService;

    //查询符合条件的患者信息
    @RequestMapping(value = "/getPatientInfo.json", method = RequestMethod.POST)
    public ApiResult getPatientInfo(@RequestBody PatientSearchDto patientSearchDto){
        try {
            return ApiResult.success(barCodePrintService.getPatientInfo(patientSearchDto));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //查询符合条件的检验申请数目
    @RequestMapping(value = "/getCheckApplicationCount.json", method = RequestMethod.POST)
    public ApiResult getCheckApplicationCount(@RequestBody CheckApplicationSearchDto checkApplicationSearchDto){
        try {
            return ApiResult.success(barCodePrintService.getCheckApplicationCount(checkApplicationSearchDto));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //查询符合条件的检验申请
    @RequestMapping(value = "/getCheckApplication.json", method = RequestMethod.POST)
    public ApiResult getCheckApplication(@RequestBody CheckApplicationSearchDto checkApplicationSearchDto){
        try {
            return ApiResult.success(barCodePrintService.getCheckApplication(checkApplicationSearchDto));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //查询打印条码
    @RequestMapping(value = "/getPrintBarCode.json", method = RequestMethod.POST)
    public ApiResult getPrintBarCode(@RequestBody List<String> checkApplicationIdList) {
        try {
            return ApiResult.success(barCodePrintService.getPrintBarCode(checkApplicationIdList));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }
}
