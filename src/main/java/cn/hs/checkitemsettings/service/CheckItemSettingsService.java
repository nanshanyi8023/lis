package cn.hs.checkitemsettings.service;

import cn.hs.checkitemsettings.mapper.CheckItemMapper;
import cn.hs.checkitemsettings.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckItemSettingsService {
    @Autowired
    private CheckItemMapper checkItemMapper;

    public List<CheckItem> getCheckItems(String hosNum, String inputValue) {
        List<CheckItem> checkItems = checkItemMapper.getCheckItems(hosNum, inputValue);
        return checkItems;
    }

    public int deleteCheckItems(String hosNum, List<String> itemIdList){
        if (itemIdList.isEmpty()){
            return 0;
        }
        return checkItemMapper.deleteCheckItems(hosNum, itemIdList);
    }
}
