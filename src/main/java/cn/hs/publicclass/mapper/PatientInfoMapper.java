package cn.hs.publicclass.mapper;

import cn.hs.barcodeprint.dto.PatientSearchDto;
import cn.hs.publicclass.table.patientinfo.PatientInfo;
import cn.hs.publicclass.table.patientinfo.PatientInfoExample;
import cn.hs.publicclass.table.patientinfo.PatientInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PatientInfoMapper {
    long countByExample(PatientInfoExample example);

    int deleteByExample(PatientInfoExample example);

    int deleteByPrimaryKey(PatientInfoKey key);

    int insert(PatientInfo record);

    int insertSelective(PatientInfo record);

    List<PatientInfo> selectByExample(PatientInfoExample example);

    PatientInfo selectByPrimaryKey(PatientInfoKey key);

    int updateByExampleSelective(@Param("record") PatientInfo record, @Param("example") PatientInfoExample example);

    int updateByExample(@Param("record") PatientInfo record, @Param("example") PatientInfoExample example);

    int updateByPrimaryKeySelective(PatientInfo record);

    int updateByPrimaryKey(PatientInfo record);

    /**
     * 自定义方法
     */
    //根据患者id和姓名查找对应的患者
    List<PatientInfo> getPatientInfo(@Param("hosNum") String hosNum, @Param("patientSearchDto") PatientSearchDto patientSearchDto);
}