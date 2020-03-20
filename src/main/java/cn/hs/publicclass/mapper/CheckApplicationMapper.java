package cn.hs.publicclass.mapper;

import cn.hs.publicclass.table.checkapplication.CheckApplication;
import cn.hs.publicclass.table.checkapplication.CheckApplicationExample;
import cn.hs.publicclass.table.checkapplication.CheckApplicationKey;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CheckApplicationMapper {
    long countByExample(CheckApplicationExample example);

    int deleteByExample(CheckApplicationExample example);

    int deleteByPrimaryKey(CheckApplicationKey key);

    int insert(CheckApplication record);

    int insertSelective(CheckApplication record);

    List<CheckApplication> selectByExample(CheckApplicationExample example);

    CheckApplication selectByPrimaryKey(CheckApplicationKey key);

    int updateByExampleSelective(@Param("record") CheckApplication record, @Param("example") CheckApplicationExample example);

    int updateByExample(@Param("record") CheckApplication record, @Param("example") CheckApplicationExample example);

    int updateByPrimaryKeySelective(CheckApplication record);

    int updateByPrimaryKey(CheckApplication record);


    /**
     * 自定义方法
     */
    //根据患者id和开单时间查找对应的检验申请
    List<CheckApplication> selectByPatientAndTime(@Param("hosNum") String hosNum, @Param("patientIdList") List<String> patientIdList,
                                                  @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    //查找检验申请对应的条码
    String selectBarcodeNnumber(@Param("hosNum") String hosNum, @Param("checkApplicationId") String checkApplicationId);

    //获取最大条码号
    String getMaxBarcodeNnumber(@Param("hosNum") String hosNum);

    //更新条码号
    void updateBarcodeNnumber(@Param("hosNum") String hosNum, @Param("checkApplicationId") String checkApplicationId, @Param("barcodeNnumber") String barcodeNnumber);
}