package cn.hs.barcodeprint.controller;

import cn.hs.barcodeprint.dto.CheckApplicationSearchDto;
import cn.hs.barcodeprint.dto.PatientSearchDto;
import cn.hs.barcodeprint.service.BarCodePrintService;
import cn.hs.publicclass.method.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    //查询符合条件的检验申请
    @RequestMapping(value = "/getCheckApplication.json", method = RequestMethod.POST)
    public ApiResult getCheckApplication(@RequestBody CheckApplicationSearchDto checkApplicationSearchDto){
        try {
            return ApiResult.success(barCodePrintService.getCheckApplication(checkApplicationSearchDto));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }
}
