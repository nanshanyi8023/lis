package cn.hs.checkitemsettings.mapper;

import cn.hs.checkitemsettings.pojo.CheckItem;
import cn.hs.checkitemsettings.pojo.CheckItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckItemMapper {
    long countByExample(CheckItemExample example);

    int deleteByExample(CheckItemExample example);

    int deleteByPrimaryKey(String itemId);

    int insert(CheckItem record);

    int insertSelective(CheckItem record);

    List<CheckItem> selectByExample(CheckItemExample example);

    CheckItem selectByPrimaryKey(String itemId);

    int updateByExampleSelective(@Param("record") CheckItem record, @Param("example") CheckItemExample example);

    int updateByExample(@Param("record") CheckItem record, @Param("example") CheckItemExample example);

    int updateByPrimaryKeySelective(CheckItem record);

    int updateByPrimaryKey(CheckItem record);

    /**
     * 自定义方法
     */
    //根据id,名称或英文缩写模糊搜索检验项目
    List<CheckItem> getCheckItems(@Param("hosNum") String hosNum, @Param("inputValue") String inputValue);
    //删除选中检验项目
    int deleteCheckItems(@Param("hosNum") String hosNum, @Param("list") List<String> itemIdList);
}