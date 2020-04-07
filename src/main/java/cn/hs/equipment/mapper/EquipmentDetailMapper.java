package cn.hs.equipment.mapper;

import cn.hs.equipment.pojo.EquipmentDetail;
import cn.hs.equipment.pojo.EquipmentDetailExample;
import cn.hs.equipment.pojo.EquipmentDetailKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentDetailMapper {
    long countByExample(EquipmentDetailExample example);

    int deleteByExample(EquipmentDetailExample example);

    int deleteByPrimaryKey(EquipmentDetailKey key);

    int insert(EquipmentDetail record);

    int insertSelective(EquipmentDetail record);

    List<EquipmentDetail> selectByExample(EquipmentDetailExample example);

    EquipmentDetail selectByPrimaryKey(EquipmentDetailKey key);

    int updateByExampleSelective(@Param("record") EquipmentDetail record, @Param("example") EquipmentDetailExample example);

    int updateByExample(@Param("record") EquipmentDetail record, @Param("example") EquipmentDetailExample example);

    int updateByPrimaryKeySelective(EquipmentDetail record);

    int updateByPrimaryKey(EquipmentDetail record);

    //查找所有检验设备id和检验项目id
    List<EquipmentDetail> getEquipmentIdAndCheckItemId(@Param("hosNum") String hosNum);
}