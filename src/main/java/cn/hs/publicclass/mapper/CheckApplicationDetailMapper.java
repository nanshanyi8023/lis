package cn.hs.publicclass.mapper;

import cn.hs.publicclass.table.checkapplicationdetail.CheckApplicationDetail;
import cn.hs.publicclass.table.checkapplicationdetail.CheckApplicationDetailExample;
import cn.hs.publicclass.table.checkapplicationdetail.CheckApplicationDetailKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckApplicationDetailMapper {
    long countByExample(CheckApplicationDetailExample example);

    int deleteByExample(CheckApplicationDetailExample example);

    int deleteByPrimaryKey(CheckApplicationDetailKey key);

    int insert(CheckApplicationDetail record);

    int insertSelective(CheckApplicationDetail record);

    List<CheckApplicationDetail> selectByExample(CheckApplicationDetailExample example);

    CheckApplicationDetail selectByPrimaryKey(CheckApplicationDetailKey key);

    int updateByExampleSelective(@Param("record") CheckApplicationDetail record, @Param("example") CheckApplicationDetailExample example);

    int updateByExample(@Param("record") CheckApplicationDetail record, @Param("example") CheckApplicationDetailExample example);

    int updateByPrimaryKeySelective(CheckApplicationDetail record);

    int updateByPrimaryKey(CheckApplicationDetail record);

    /**
     * 自定义方法
     */
    //根据检验申请id查询对应的检验项目组合
    List<CheckApplicationDetail> getCheckItemGroup(@Param("hosnum") String hosnum, @Param("checkApplicationIdList") List<String> checkApplicationIdList);

}