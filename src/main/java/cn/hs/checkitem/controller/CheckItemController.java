package cn.hs.checkitem.controller;

import cn.hs.publicclass.method.ApiResult;
import cn.hs.checkitem.pojo.CheckItem;
import cn.hs.checkitem.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkItemSettings")
public class CheckItemController {
    @Autowired
    private CheckItemService checkItemService;

    //查找检验项目
    @RequestMapping(value = "/getCheckItems.json", method = RequestMethod.GET)
    public ApiResult getCheckItems(@RequestParam(required = false) String workGroupId,
                                   @RequestParam(required = false) String checkItemGroupId,
                                   @RequestParam(required = false) String checkItem){
        try {
            return ApiResult.success(checkItemService.getCheckItems(workGroupId,checkItemGroupId,checkItem));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //删除检验项目
    @RequestMapping(value = "/deleteCheckItems.json", method = RequestMethod.POST)
    public ApiResult deleteCheckItems(@RequestBody List<String> itemIdList){
        try {
            return ApiResult.success(checkItemService.deleteCheckItems(itemIdList));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //保存检验项目
    @RequestMapping(value = "/saveCheckItem.json", method = RequestMethod.POST)
    public ApiResult saveCheckItem(@RequestBody CheckItem checkItem){
        try {
            checkItemService.saveCheckItem(checkItem);
            return ApiResult.success();
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //查找所有工作组
    @RequestMapping(value = "/getAllWorkGroup.json", method = RequestMethod.GET)
    public ApiResult getAllWorkGroup(){
        try {
            return ApiResult.success(checkItemService.getAllWorkGroup());
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //查找所有检验项目组合
    @RequestMapping(value = "/getAllCheckItemGroup.json", method = RequestMethod.GET)
    public ApiResult getAllCheckItemGroup(@RequestParam(required = true) String workGroupId){
        try {
            return ApiResult.success(checkItemService.getAllCheckItemGroup(workGroupId));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }
}
