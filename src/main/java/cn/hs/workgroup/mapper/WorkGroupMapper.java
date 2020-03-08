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

}