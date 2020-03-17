package cn.hs.publicclass.mapper;

import cn.hs.publicclass.table.checkapplicationform.CheckApplicationForm;
import cn.hs.publicclass.table.checkapplicationform.CheckApplicationFormExample;
import cn.hs.publicclass.table.checkapplicationform.CheckApplicationFormKey;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckApplicationFormMapper {
    long countByExample(CheckApplicationFormExample example);

    int deleteByExample(CheckApplicationFormExample example);

    int deleteByPrimaryKey(CheckApplicationFormKey key);

    int insert(CheckApplicationForm record);

    int insertSelective(CheckApplicationForm record);

    List<CheckApplicationForm> selectByExample(CheckApplicationFormExample example);

    CheckApplicationForm selectByPrimaryKey(CheckApplicationFormKey key);

    int updateByExampleSelective(@Param("record") CheckApplicationForm record, @Param("example") CheckApplicationFormExample example);

    int updateByExample(@Param("record") CheckApplicationForm record, @Param("example") CheckApplicationFormExample example);

    int updateByPrimaryKeySelective(CheckApplicationForm record);

    int updateByPrimaryKey(CheckApplicationForm record);


    /**
     * 自定义方法
     */
    //根据患者id和开单时间查找对应的检验申请
    List<CheckApplicationForm> selectByPatientAndTime(@Param("hosNum") String hosNum, @Param("patientIdList") List<String> patientIdList,
                                                      @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}