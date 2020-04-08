package cn.hs.equipment.service;

import cn.hs.equipment.mapper.EquipmentDetailMapper;
import cn.hs.publicclass.method.BusinessException;
import cn.hs.publicclass.method.GetCookieService;
import cn.hs.equipment.mapper.EquipmentMapper;
import cn.hs.equipment.pojo.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private EquipmentDetailMapper equipmentDetailMapper;

    @Autowired
    private GetCookieService getCookie;

    //查找检验仪器
    public List<Equipment> getEquipments(String equipment) {
        return equipmentMapper.getEquipments(getCookie.getHosNum(),equipment);
    }

    //删除选中检验仪器
    public int deleteEquipments(List<String> itemIdList) {
        if (itemIdList.isEmpty()){
            return 0;
        }
        //删除选中检验设备与对应的检验项目之间的关系
        equipmentDetailMapper.deleteByEquipmentId(getCookie.getHosNum(),itemIdList);
        return equipmentMapper.deleteEquipments(getCookie.getHosNum(),itemIdList);
    }

    //保存修改后的检验仪器
    public void saveEquipment(Equipment equipment) {
        String hosNum = getCookie.getHosNum();
        equipment.setHosnum(hosNum);
        if (StringUtils.isEmpty(equipment.getItemId())){  //新增
            if (this.isRepeat(hosNum,equipment.getItemName())){
                throw new BusinessException("检验仪器名称不可重复");
            }
            equipment.setItemId(equipmentMapper.getMaxId(hosNum));
            equipmentMapper.insertSelective(equipment);
        }else {  //更新
            equipmentMapper.updateByPrimaryKeySelective(equipment);
        }
    }

    //判断检验仪器名称是否重复
    private boolean isRepeat(String hosNum, String equipmentName) {
        List<String> list =  equipmentMapper.getAllequipmentName(hosNum);
        for (int i = 0; i < list.size(); i++) {
            if (equipmentName.equals(list.get(i))){
                return true;
            }
        }
        return false;
    }
}
