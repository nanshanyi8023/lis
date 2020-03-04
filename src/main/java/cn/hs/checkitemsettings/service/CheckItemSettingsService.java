package cn.hs.checkitemsettings.service;

import cn.hs.checkitemsettings.mapper.CheckItemMapper;
import cn.hs.checkitemsettings.pojo.CheckItem;
import cn.hs.publicmethod.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CheckItemSettingsService {
    @Autowired
    private CheckItemMapper checkItemMapper;

    //查找检验项目
    public List<CheckItem> getCheckItems(String hosNum, String inputValue) {
        List<CheckItem> checkItems = checkItemMapper.getCheckItems(hosNum, inputValue);
        return checkItems;
    }

    //删除检验项目
    public int deleteCheckItems(String hosNum, List<String> itemIdList){
        if (itemIdList.isEmpty()){
            return 0;
        }
        return checkItemMapper.deleteCheckItems(hosNum, itemIdList);
    }

    //保存检验项目
    public CheckItem saveCheckItem(String hosNum, CheckItem checkItem) {
        checkItem.setHosnum(hosNum);
        /*if (StringUtils.isEmpty(checkItem.getPySpell())) {
            checkItem.setPySpell(WordUtil.trans2PyCode(checkItem.getItemName()));
        }
        if (StringUtils.isEmpty(checkItem.getWbSpell())) {
            checkItem.setWbSpell(WordUtil.trans2PyCode(checkItem.getItemName()));
        }*/
        if (StringUtils.isEmpty(checkItem.getItemId())){  //新增
            if (this.isRepeat(hosNum,checkItem.getItemName())){
                throw new BusinessException("检验项目名称不可重复");
            }
            checkItem.setItemId(checkItemMapper.getMaxId(hosNum));
            checkItemMapper.insertSelective(checkItem);
        }else {  //更新
            checkItemMapper.updateByPrimaryKeySelective(checkItem);
        }
        return null;
    }

    //判断检验项目名称是否重复
    private boolean isRepeat(String hosNum, String itemName) {
        List<String> list =  checkItemMapper.getAllExamMethod(hosNum);
        for (int i = 0; i < list.size(); i++) {
            if (itemName.equals(list.get(i))){
                return true;
            }
        }
        return false;
    }
}
