package cn.hs.checkitemgroup.service;

import cn.hs.checkitem.mapper.CheckItemMapper;
import cn.hs.checkitem.pojo.CheckItem;
import cn.hs.checkitemgroup.mapper.CheckItemGroupDetailMapper;
import cn.hs.checkitemgroup.mapper.CheckItemGroupMapper;
import cn.hs.checkitemgroup.pojo.CheckItemGroup;
import cn.hs.equipment.mapper.EquipmentDetailMapper;
import cn.hs.equipment.pojo.EquipmentDetail;
import cn.hs.equipment.pojo.EquipmentDetailKey;
import cn.hs.publicclass.method.BusinessException;
import cn.hs.publicclass.method.GetCookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckItemGroupService {

    @Autowired
    private CheckItemGroupMapper checkItemGroupMapper;

    @Autowired
    private CheckItemGroupDetailMapper checkItemGroupDetailMapper;

    @Autowired
    private EquipmentDetailMapper equipmentDetailMapper;

    @Autowired
    private CheckItemMapper checkItemMapper;

    @Autowired
    private GetCookieService getCookie;

    //查找检验项目组合
    public List<CheckItemGroup> getcheckItemGroups(String equipmentId, String checkItemGroup) {
        List<CheckItemGroup> checkItemGroupList = checkItemGroupMapper.getcheckItemGroups(getCookie.getHosNum(), equipmentId, checkItemGroup);
        return checkItemGroupList;
    }

    //查找检验项目组合对应的检验项目
    public List<CheckItem> getcheckItemNameList(String checkItemGroupId) {
        return checkItemGroupMapper.getcheckItemNameList(getCookie.getHosNum(),checkItemGroupId);
    }

    //删除选中检验项目组合
    public int deleteCheckItemGroups(List<String> itemIdList) {
        if (itemIdList.isEmpty()){
            return 0;
        }
        //删除选中检验项目组合与对应的项目之间的关系
        checkItemGroupDetailMapper.deleteByGroupId(getCookie.getHosNum(),itemIdList);
        return checkItemGroupMapper.deleteCheckItemGroups(getCookie.getHosNum(),itemIdList);
    }

    //保存检验项目组合
    public void savecheckItemGroup(CheckItemGroup checkItemGroup) {
        String hosNum = getCookie.getHosNum();
        checkItemGroup.setHosnum(hosNum);
        //判断勾选的检验项目是否都可以在对应的检验仪器上检验
        EquipmentDetailKey equipmentDetailKey = new EquipmentDetailKey();
        equipmentDetailKey.setHosnum(hosNum);
        equipmentDetailKey.setEquipmentId(checkItemGroup.getEquipmentId());
        for (int i = 0; i < checkItemGroup.getCheckItemIdList().size(); i++) {
            equipmentDetailKey.setCheckItemId(checkItemGroup.getCheckItemIdList().get(i));
            EquipmentDetail equipmentDetail = equipmentDetailMapper.selectByPrimaryKey(equipmentDetailKey);
            if (equipmentDetail == null){
                throw new BusinessException("编号为\""+checkItemGroup.getCheckItemIdList().get(i)+"\"的检验项目不可在\""+checkItemGroup.getEquipmentName()+"\"上检验，请重新维护!");
            }
        }

        if (StringUtils.isEmpty(checkItemGroup.getGroupId())){  //新增
            if (this.isRepeat(hosNum,checkItemGroup.getGroupName())){
                throw new BusinessException("检验项目组合名称不可重复");
            }
            checkItemGroup.setGroupId(checkItemGroupMapper.getMaxId(hosNum));
            checkItemGroupMapper.insertSelective(checkItemGroup);
        }else {  //更新
            checkItemGroupMapper.updateByPrimaryKeySelective(checkItemGroup);
            //删除该检验项目组合与检验项目之前的关系
            List<String> groupIdList = new ArrayList<String>();
            groupIdList.add(checkItemGroup.getGroupId());
            checkItemGroupDetailMapper.deleteByGroupId(hosNum,groupIdList);
        }
        //插入检验项目组合与检验项目之间的关系
        if ( (checkItemGroup.getCheckItemIdList() == null) || (checkItemGroup.getCheckItemIdList().size() <= 0) ){
            return;
        }
        checkItemGroupDetailMapper.insertByGroupIdAndItemId(hosNum,checkItemGroup.getGroupId(),checkItemGroup.getCheckItemIdList());
    }

    //判断检验项目名称是否重复
    private boolean isRepeat(String hosNum, String checkItemGroupName) {
        List<String> list =  checkItemGroupMapper.getAllcheckItemGroupName(hosNum);
        for (int i = 0; i < list.size(); i++) {
            if (checkItemGroupName.equals(list.get(i))){
                return true;
            }
        }
        return false;
    }

    //查找所有样品类型
    public List<String> getAllSampleType() {
        return checkItemGroupMapper.getAllSampleType(getCookie.getHosNum());
    }

    //详细信息窗口根据输入框查找对应的检验项目id
    public String getCheckItemId(String inputValue) {
        if (StringUtils.isEmpty(inputValue)){
            return null;
        }
        return checkItemMapper.getCheckItemId(getCookie.getHosNum(),inputValue);
    }
}
