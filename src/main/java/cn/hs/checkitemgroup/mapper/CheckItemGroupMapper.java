package cn.hs.checkitemgroup.mapper;

import cn.hs.checkitem.pojo.CheckItem;
import cn.hs.checkitemgroup.pojo.CheckItemGroup;
import cn.hs.checkitemgroup.pojo.CheckItemGroupExample;
import cn.hs.checkitemgroup.pojo.CheckItemGroupKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckItemGroupMapper {
    long countByExample(CheckItemGroupExample example);

    int deleteByExample(CheckItemGroupExample example);

    int deleteByPrimaryKey(CheckItemGroupKey key);

    int insert(CheckItemGroup record);

    int insertSelective(CheckItemGroup record);

    List<CheckItemGroup> selectByExample(CheckItemGroupExample example);

    CheckItemGroup selectByPrimaryKey(CheckItemGroupKey key);

    int updateByExampleSelective(@Param("record") CheckItemGroup record, @Param("example") CheckItemGroupExample example);

    int updateByExample(@Param("record") CheckItemGroup record, @Param("example") CheckItemGroupExample example);

    int updateByPrimaryKeySelective(CheckItemGroup record);

    int updateByPrimaryKey(CheckItemGroup record);

    /**
     * 自定义方法
     */
    //根据医院号和检验设备id查找所有的检验项目组合id和名称
    List<CheckItemGroup> selectAllCheckItemGroup(@Param("hosNum") String hosNum, @Param("equipmentId") String equipmentId);

    //查找检验项目组合
    List<CheckItemGroup> getcheckItemGroups(@Param("hosNum") String hosNum, @Param("equipmentId") String equipmentId, @Param("checkItemGroup") String checkItemGroup);

    //根据检验项目组合id删除对应的检验项目组合
    int deleteCheckItemGroups(@Param("hosNum") String hosNum, @Param("list") List<String> itemIdList);

    //查找所有检验项目名称
    List<String> getAllcheckItemGroupName(@Param("hosNum") String hosNum);

    //获取最大检验项目组合id
    String getMaxId(@Param("hosNum") String hosNum);

    //查找所有样品类型
    List<String> getAllSampleType(@Param("hosNum") String hosNum);

    //查找检验项目组合对应的检验项目
    List<CheckItem> getcheckItemNameList(@Param("hosNum") String hosNum, @Param("checkItemGroupId") String checkItemGroupId);

    //查找检验项目对应的检验项目组合
    List<CheckItemGroup> getAssociatedCheckItemGroup(@Param("hosNum") String hosNum, @Param("checkItemId") String checkItemId);
}