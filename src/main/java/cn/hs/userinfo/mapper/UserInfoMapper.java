package cn.hs.userinfo.mapper;

import cn.hs.userinfo.pojo.UserInfo;
import cn.hs.userinfo.pojo.UserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    long countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int deleteByPrimaryKey(String loginName);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByExample(UserInfoExample example);

    UserInfo selectByPrimaryKey(String loginName);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);


    /**
     * 自定义方法
     */
    //根据登录用户名和密码确认身份
    UserInfo selectByLogin(@Param("loginName") String loginName, @Param("oldPassword") String oldPassword);

    //修改密码
    void updateLoginPassword(@Param("loginName") String loginName, @Param("newPassword") String newPassword);
}