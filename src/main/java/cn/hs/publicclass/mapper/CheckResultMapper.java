package cn.hs.publicclass.mapper;

import cn.hs.publicclass.table.checkresult.CheckResult;
import cn.hs.publicclass.table.checkresult.CheckResultExample;
import cn.hs.publicclass.table.checkresult.CheckResultKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckResultMapper {
    long countByExample(CheckResultExample example);

    int deleteByExample(CheckResultExample example);

    int deleteByPrimaryKey(CheckResultKey key);

    int insert(CheckResult record);

    int insertSelective(CheckResult record);

    List<CheckResult> selectByExample(CheckResultExample example);

    CheckResult selectByPrimaryKey(CheckResultKey key);

    int updateByExampleSelective(@Param("record") CheckResult record, @Param("example") CheckResultExample example);

    int updateByExample(@Param("record") CheckResult record, @Param("example") CheckResultExample example);

    int updateByPrimaryKeySelective(CheckResult record);

    int updateByPrimaryKey(CheckResult record);



    /**
     * 查找保存过的检验结果
     * @param hosNum
     * @param checkApplicationId
     * @return List<CheckResult>
     */
    List<CheckResult> getCheckResult(@Param("hosNum") String hosNum, @Param("checkApplicationId") String checkApplicationId);
}