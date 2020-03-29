package cn.hs.publicclass.mapper;

import cn.hs.barcodeprint.dto.CheckApplicationSearchDto;
import cn.hs.publicclass.table.checkapplication.CheckApplication;
import cn.hs.publicclass.table.checkapplication.CheckApplicationExample;
import cn.hs.publicclass.table.checkapplication.CheckApplicationKey;
import java.util.List;

import cn.hs.samplerecept.dto.ReceptedSampleQueryDto;
import cn.hs.samplerecept.dto.RetrunSampleDto;
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
     * 条码打印页面
     */
    //根据患者id和开单时间查找对应的检验申请
    List<CheckApplication> selectByPatientAndTime(@Param("hosNum") String hosNum, @Param("checkApplicationSearchDto") CheckApplicationSearchDto checkApplicationSearchDto);

    //查找检验申请对应的条码
    String selectBarcodeNnumber(@Param("hosNum") String hosNum, @Param("checkApplicationId") String checkApplicationId);

    //获取最大条码号
    String getMaxBarcodeNnumber(@Param("hosNum") String hosNum);

    //更新条码号
    void updateBarcodeNnumber(@Param("hosNum") String hosNum, @Param("checkApplicationId") String checkApplicationId, @Param("barcodeNnumber") String barcodeNnumber);

    /**
     * 样本接收页面
     */
    //查找对应的已接收样本
    List<CheckApplication> selectReceptedSample(@Param("hosNum") String hosNum, @Param("receptedSampleQueryDto") ReceptedSampleQueryDto receptedSampleQueryDto);

    //根据条码号查找对应的检验申请（判断条码号是否存在）
    CheckApplication getCheckApplication(@Param("hosNum") String hosNum, @Param("barCodeNumber") String barCodeNumber);

    //查找条码号对应的样本接收状态
    CheckApplication getReceptionStatu(@Param("hosNum") String hosNum, @Param("barCodeNumber") String barCodeNumber);

    //根据条码号接收样本
    void receiveSample(@Param("hosNum") String hosNum, @Param("barCodeNumber") String barCodeNumber);

    //退回样本
    int returnSample(@Param("hosNum") String hosNum, @Param("retrunSampleDto") RetrunSampleDto retrunSampleDto);

    /**
     * 样本退回页面
     */
    //查找对应的已退回样本
    List<CheckApplication> selectReturnedSample(@Param("hosNum") String hosNum, @Param("returnSampleQueryDto") ReceptedSampleQueryDto returnSampleQueryDto);

}