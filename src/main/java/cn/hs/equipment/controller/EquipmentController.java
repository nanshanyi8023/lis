package cn.hs.equipment.controller;

import cn.hs.publicclass.method.ApiResult;
import cn.hs.equipment.pojo.Equipment;
import cn.hs.equipment.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipmentSettings")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    //查找检验仪器
    @RequestMapping(value = "/getEquipments.json", method = RequestMethod.GET)
    public ApiResult getEquipments(@RequestParam(required = false) String equipment){
        try {
            return ApiResult.success(equipmentService.getEquipments(equipment));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //删除选中检验仪器
    @RequestMapping(value = "/deleteEquipments.json", method = RequestMethod.POST)
    public ApiResult deleteEquipments(@RequestBody List<String> itemIdList){
        try {
            return ApiResult.success(equipmentService.deleteEquipments(itemIdList));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //保存修改后的检验仪器
    @RequestMapping(value = "/saveEquipment.json", method = RequestMethod.POST)
    public ApiResult saveEquipment(@RequestBody Equipment equipment){
        try {
            equipmentService.saveEquipment(equipment);
            return ApiResult.success();
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }
}
