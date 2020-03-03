package cn.hs.checkitemsettings.controller;

import cn.hs.ApiResult;
import cn.hs.checkitemsettings.service.CheckItemSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkItemSettings")
public class CheckItemSettingsController {
    @Autowired
    private CheckItemSettingsService checkItemSettingsService;

    private String hosNum;

    //查找检验项目
    @RequestMapping(value = "/getCheckItems.json", method = RequestMethod.GET)
    public ApiResult getCheckItems(@RequestParam(required = true) String hosNum,@RequestParam(required = false) String inputValue){
        try {
            this.hosNum = hosNum;
            return ApiResult.success(checkItemSettingsService.getCheckItems(hosNum,inputValue));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //删除检验项目
    @RequestMapping(value = "/deleteCheckItems.json", method = RequestMethod.POST)
    public ApiResult deleteCheckItems(@RequestBody List<String> itemIdList){
        try {
            return ApiResult.success(checkItemSettingsService.deleteCheckItems(hosNum, itemIdList));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

}
