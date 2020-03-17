package cn.hs.barcodeprint.controller;

import cn.hs.barcodeprint.service.BarCodePrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barCodePrint")
public class BarCodePrintController {

    @Autowired
    private BarCodePrintService barCodePrintService;


}
