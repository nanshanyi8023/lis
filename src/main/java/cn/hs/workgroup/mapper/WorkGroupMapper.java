package cn.hs.workgroup.mapper;

import cn.hs.workgroup.pojo.WorkGroup;
import cn.hs.workgroup.pojo.WorkGroupExample;
import cn.hs.workgroup.pojo.WorkGroupKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkGroupMapper {

    long countByExample(WorkGroupExample example);

    int deleteByExample(WorkGroupExample example);

    int deleteByPrimaryKey(WorkGroupKey key);

    int insert(WorkGroup record);

    int insertSelective(WorkGroup record);

    List<WorkGroup> selectByExample(WorkGroupExample example);

    WorkGroup selectByPrimaryKey(WorkGroupKey key);

    int updateByExampleSelective(@Param("record") WorkGroup record, @Param("example") WorkGroupExample example);

    int updateByExample(@Param("record") WorkGroup record, @Param("example") WorkGroupExample example);

    int updateByPrimaryKeySelective(WorkGroup record);

    int updateByPrimaryKey(WorkGroup record);


    /**
     * 自定义方法
     */
    //查找所有的工作组id和名称
    List<WorkGroup> selectAllWorkGroup(@Param("hosNum") String hosNum);

    //根据工作组id或工作组名称查找对应的工作组
    List<WorkGroup> getworkGroups(@Param("hosNum")String hosNum, @Param("workGroup") String workGroup);

    //根据工作组id删除对应的工作组
    int deleteWorkGroups(@Param("hosNum") String hosNum, @Param("list") List<String> itemIdList);

    //查找所有的工作组名称
    List<String> getAllWorkGroupName(@Param("hosNum") String hosNum);

    //查找最大的工作组id
    String getMaxId(@Param("hosNum") String hosNum);

    //根据组类型查找组代码
    String getGroupCode(@Param("hosNum") String hosNum, @Param("groupType") String groupType);
}