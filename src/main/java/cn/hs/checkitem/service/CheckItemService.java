package cn.hs.checkitem.service;

import cn.hs.checkitem.mapper.CheckItemMapper;
import cn.hs.checkitem.pojo.CheckItem;
import cn.hs.checkitemgroup.mapper.CheckItemGroupDetailMapper;
import cn.hs.checkitemgroup.mapper.CheckItemGroupMapper;
import cn.hs.checkitemgroup.pojo.CheckItemGroup;
import cn.hs.equipment.mapper.EquipmentDetailMapper;
import cn.hs.equipment.mapper.EquipmentMapper;
import cn.hs.equipment.pojo.Equipment;
import cn.hs.publicclass.method.BusinessException;
import cn.hs.publicclass.method.GetCookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CheckItemService {
    @Autowired
    private CheckItemMapper checkItemMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private CheckItemGroupMapper checkItemGroupMapper;

    @Autowired
    private EquipmentDetailMapper equipmentDetailMapper;

    @Autowired
    private CheckItemGroupDetailMapper checkItemGroupDetailMapper;

    @Autowired
    private GetCookieService getCookie;

    //查找检验项目
    public List<CheckItem> getCheckItems(String equipmentId, String checkItemGroupId, String checkItem) {
        return checkItemMapper.getCheckItems(getCookie.getHosNum(), equipmentId,checkItemGroupId,checkItem);
    }

    //删除检验项目
    public int deleteCheckItems(List<String> itemIdList){
        if (itemIdList.isEmpty()){
            return 0;
        }
        //删除检验项目——检验仪器之间的关联
        equipmentDetailMapper.deleteByCheckItemId(getCookie.getHosNum(),itemIdList);
        //删除检验项目——检验项目组合之间的关联
        checkItemGroupDetailMapper.deleteByCheckItemId(getCookie.getHosNum(),itemIdList);

        return checkItemMapper.deleteCheckItems(getCookie.getHosNum(), itemIdList);
    }

    //保存检验项目
    public void saveCheckItem(CheckItem checkItem) {
        String hosNum = getCookie.getHosNum();
        checkItem.setHosnum(hosNum);
        if (StringUtils.isEmpty(checkItem.getItemId())){  //新增
            if (this.isRepeat(hosNum,checkItem.getItemName())){
                throw new BusinessException("检验项目名称不可重复");
            }
            checkItem.setItemId(checkItemMapper.getMaxId(hosNum));
            checkItemMapper.insertSelective(checkItem);
        }else {  //更新
            checkItemMapper.updateByPrimaryKeySelective(checkItem);
        }
    }

    //判断检验项目名称是否重复
    private boolean isRepeat(String hosNum, String itemName) {
        List<String> list =  checkItemMapper.getAllCheckItemName(hosNum);
        for (int i = 0; i < list.size(); i++) {
            if (itemName.equals(list.get(i))){
                return true;
            }
        }
        return false;
    }

    //根据医院号查找所有检验设备
    public List<Equipment> getAllEquipment() {
        return equipmentMapper.selectAllEquipment(getCookie.getHosNum());
    }

    //根据医院号和工作组id查找所有检验项目组合
    public List<CheckItemGroup> getAllCheckItemGroup(String equipmentId) {
        return checkItemGroupMapper.selectAllCheckItemGroup(getCookie.getHosNum(),equipmentId);
    }

    //查找选中的检验项目对应的检验设备
    public List<Equipment> getAssociatedEquipment(String checkItemId) {
        return equipmentMapper.getAssociatedEquipment(getCookie.getHosNum(),checkItemId);
    }

    //查找选中的检验项目对应的检验项目组合
    public List<CheckItemGroup> getAssociatedCheckItemGroup(String checkItemId) {
        return checkItemGroupMapper.getAssociatedCheckItemGroup(getCookie.getHosNum(),checkItemId);
    }
}
