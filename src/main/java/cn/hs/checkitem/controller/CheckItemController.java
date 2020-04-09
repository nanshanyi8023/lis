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
    public ApiResult getCheckItems(@RequestParam(required = false) String equipmentId,
                                   @RequestParam(required = false) String checkItemGroupId,
                                   @RequestParam(required = false) String checkItem){
        try {
            return ApiResult.success(checkItemService.getCheckItems(equipmentId,checkItemGroupId,checkItem));
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

    //查找所有检验设备
    @RequestMapping(value = "/getAllEquipment.json", method = RequestMethod.GET)
    public ApiResult getAllEquipment(){
        try {
            return ApiResult.success(checkItemService.getAllEquipment());
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //查找所有检验项目组合
    @RequestMapping(value = "/getAllCheckItemGroup.json", method = RequestMethod.GET)
    public ApiResult getAllCheckItemGroup(@RequestParam(required = false) String equipmentId){
        try {
            return ApiResult.success(checkItemService.getAllCheckItemGroup(equipmentId));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //查找选中的检验项目对应的检验设备
    @RequestMapping(value = "/getAssociatedEquipment.json", method = RequestMethod.GET)
    public ApiResult getAssociatedEquipment(@RequestParam(required = true) String checkItemId){
        try {
            return ApiResult.success(checkItemService.getAssociatedEquipment(checkItemId));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //查找选中的检验项目对应的检验项目组合
    @RequestMapping(value = "/getAssociatedCheckItemGroup.json", method = RequestMethod.GET)
    public ApiResult getAssociatedCheckItemGroup(@RequestParam(required = true) String checkItemId){
        try {
            return ApiResult.success(checkItemService.getAssociatedCheckItemGroup(checkItemId));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //详细信息窗口根据输入框查找对应的检验项目组合id
    @RequestMapping(value = "/getCheckItemGroupId.json", method = RequestMethod.GET)
    public ApiResult getCheckItemGroupId(@RequestParam(required = true) String inputValue){
        try {
            return ApiResult.success(checkItemService.getCheckItemGroupId(inputValue));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }
}
