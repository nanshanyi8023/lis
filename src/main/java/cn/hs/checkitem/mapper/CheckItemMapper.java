package cn.hs.checkitem.mapper;

import cn.hs.checkitem.pojo.CheckItem;
import cn.hs.checkitem.pojo.CheckItemExample;
import cn.hs.checkitem.pojo.CheckItemKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckItemMapper {
    long countByExample(CheckItemExample example);

    int deleteByExample(CheckItemExample example);

    int deleteByPrimaryKey(CheckItemKey key);

    int insert(CheckItem record);

    int insertSelective(CheckItem record);

    List<CheckItem> selectByExample(CheckItemExample example);

    CheckItem selectByPrimaryKey(CheckItemKey key);

    int updateByExampleSelective(@Param("record") CheckItem record, @Param("example") CheckItemExample example);

    int updateByExample(@Param("record") CheckItem record, @Param("example") CheckItemExample example);

    int updateByPrimaryKeySelective(CheckItem record);

    int updateByPrimaryKey(CheckItem record);


    /**
     * 自定义方法
     */
    //根据id,名称或英文缩写模糊搜索检验项目
    List<CheckItem> getCheckItems(@Param("hosNum") String hosNum, @Param("workGroupId") String workGroupId,
                                  @Param("checkItemGroupId") String checkItemGroupId,@Param("checkItem") String checkItem);

    //删除选中检验项目
    int deleteCheckItems(@Param("hosNum") String hosNum, @Param("list") List<String> itemIdList);

    //获取所有检验项目名称
    List<String> getAllExamMethod(@Param("hosNum")String hosNum);

    //获取最大的检验项目id值
    String getMaxId(@Param("hosNum")String hosNum);
}