package cn.hs.workgroup.mapper;

import cn.hs.workgroup.pojo.WorkGroupDetail;
import cn.hs.workgroup.pojo.WorkGroupDetailExample;
import cn.hs.workgroup.pojo.WorkGroupDetailKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkGroupDetailMapper {
    long countByExample(WorkGroupDetailExample example);

    int deleteByExample(WorkGroupDetailExample example);

    int deleteByPrimaryKey(WorkGroupDetailKey key);

    int insert(WorkGroupDetail record);

    int insertSelective(WorkGroupDetail record);

    List<WorkGroupDetail> selectByExample(WorkGroupDetailExample example);

    WorkGroupDetail selectByPrimaryKey(WorkGroupDetailKey key);

    int updateByExampleSelective(@Param("record") WorkGroupDetail record, @Param("example") WorkGroupDetailExample example);

    int updateByExample(@Param("record") WorkGroupDetail record, @Param("example") WorkGroupDetailExample example);

    int updateByPrimaryKeySelective(WorkGroupDetail record);

    int updateByPrimaryKey(WorkGroupDetail record);


    //查找所有工作组id和检验项目id
    List<WorkGroupDetail> getWorkGroupIdAndCheckItemId(@Param("hosNum") String hosNum);
}