package cn.hs.checkitemgroup.mapper;

import cn.hs.checkitemgroup.pojo.CheckItemGroupDetail;
import cn.hs.checkitemgroup.pojo.CheckItemGroupDetailExample;
import cn.hs.checkitemgroup.pojo.CheckItemGroupDetailKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckItemGroupDetailMapper {
    long countByExample(CheckItemGroupDetailExample example);

    int deleteByExample(CheckItemGroupDetailExample example);

    int deleteByPrimaryKey(CheckItemGroupDetailKey key);

    int insert(CheckItemGroupDetail record);

    int insertSelective(CheckItemGroupDetail record);

    List<CheckItemGroupDetail> selectByExample(CheckItemGroupDetailExample example);

    CheckItemGroupDetail selectByPrimaryKey(CheckItemGroupDetailKey key);

    int updateByExampleSelective(@Param("record") CheckItemGroupDetail record, @Param("example") CheckItemGroupDetailExample example);

    int updateByExample(@Param("record") CheckItemGroupDetail record, @Param("example") CheckItemGroupDetailExample example);

    int updateByPrimaryKeySelective(CheckItemGroupDetail record);

    int updateByPrimaryKey(CheckItemGroupDetail record);


    //根据医院号查找所有检验项目组合id和检验项目id
    List<CheckItemGroupDetail> getItemGroupIdAndItemId(@Param("hosNum") String hosNum);

    //删除选中检验项目组合与对应的项目之间的关系
    int deleteByGroupId(@Param("hosNum") String hosNum, @Param("list") List<String> itemIdList);

    //插入检验项目组合与检验项目之间的关系
    int insertByGroupIdAndItemId(@Param("hosNum") String hosNum, @Param("groupId") String groupId, @Param("checkItemIdList") List<String> checkItemIdList);
}