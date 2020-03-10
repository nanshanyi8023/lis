package cn.hs.checkitemgroup.controller;

import cn.hs.checkitemgroup.pojo.CheckItemGroup;
import cn.hs.checkitemgroup.service.CheckItemGroupService;
import cn.hs.publicmethod.ApiResult;
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
    public ApiResult getcheckItemGroups(@RequestParam(required = false) String workGroupId, @RequestParam(required = false) String checkItemGroup){
        try {
            return ApiResult.success(checkItemGroupService.getcheckItemGroups(workGroupId, checkItemGroup));
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
