package cn.hs.equipment.mapper;

import cn.hs.equipment.pojo.Equipment;
import cn.hs.equipment.pojo.EquipmentExample;
import cn.hs.equipment.pojo.EquipmentKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentMapper {
    long countByExample(EquipmentExample example);

    int deleteByExample(EquipmentExample example);

    int deleteByPrimaryKey(EquipmentKey key);

    int insert(Equipment record);

    int insertSelective(Equipment record);

    List<Equipment> selectByExample(EquipmentExample example);

    Equipment selectByPrimaryKey(EquipmentKey key);

    int updateByExampleSelective(@Param("record") Equipment record, @Param("example") EquipmentExample example);

    int updateByExample(@Param("record") Equipment record, @Param("example") EquipmentExample example);

    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);

    /**
     * 自定义方法
     */
    //查找所有的检验设备id和名称
    List<Equipment> selectAllEquipment(@Param("hosNum") String hosNum);

    //根据检验设备id或检验设备名称查找对应的检验设备
    List<Equipment> getEquipments(@Param("hosNum")String hosNum, @Param("equipment") String equipment);

    //根据检验设备id删除对应的检验设备
    int deleteEquipments(@Param("hosNum") String hosNum, @Param("list") List<String> itemIdList);

    //查找所有的检验设备名称
    List<String> getAllequipmentName(@Param("hosNum") String hosNum);

    //查找最大的检验设备id
    String getMaxId(@Param("hosNum") String hosNum);
}