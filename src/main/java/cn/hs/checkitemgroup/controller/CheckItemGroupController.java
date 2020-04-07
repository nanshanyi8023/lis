package cn.hs.checkitemgroup.controller;

import cn.hs.checkitemgroup.pojo.CheckItemGroup;
import cn.hs.checkitemgroup.service.CheckItemGroupService;
import cn.hs.publicclass.method.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkItemGroupSettings")
public class CheckItemGroupController {

    @Autowired
    private CheckItemGroupService checkItemGroupService;

    //查找检验项目组合
    @RequestMapping(value = "/getcheckItemGroups.json", method = RequestMethod.GET)
    public ApiResult getcheckItemGroups(@RequestParam(required = false) String equipmentId, @RequestParam(required = false) String checkItemGroup){
        try {
            return ApiResult.success(checkItemGroupService.getcheckItemGroups(equipmentId, checkItemGroup));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //查找检验项目组合对应的检验项目
    @RequestMapping(value = "/getcheckItemNameList.json", method = RequestMethod.GET)
    public ApiResult getcheckItemNameList(@RequestParam(required = true) String checkItemGroupId){
        try {
            return ApiResult.success(checkItemGroupService.getcheckItemNameList(checkItemGroupId));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //删除选中检验项目组合
    @RequestMapping(value = "/deleteCheckItemGroups.json", method = RequestMethod.POST)
    public ApiResult deleteCheckItemGroups(@RequestBody List<String> itemIdList){
        try {
            return ApiResult.success(checkItemGroupService.deleteCheckItemGroups(itemIdList));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //保存检验项目组合
    @RequestMapping(value = "/savecheckItemGroup.json", method = RequestMethod.POST)
    public ApiResult savecheckItemGroup(@RequestBody CheckItemGroup checkItemGroup){
        try {
            checkItemGroupService.savecheckItemGroup(checkItemGroup);
            return ApiResult.success();
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //查找所有样品类型
    @RequestMapping(value = "/getAllSampleType.json", method = RequestMethod.GET)
    public ApiResult getAllSampleType(){
        try {
            return ApiResult.success(checkItemGroupService.getAllSampleType());
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }
}
