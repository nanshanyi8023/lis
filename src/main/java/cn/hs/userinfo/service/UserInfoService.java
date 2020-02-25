package cn.hs.userinfo.service;

import cn.hs.userinfo.dto.Password;
import cn.hs.userinfo.mapper.UserInfoMapper;
import cn.hs.userinfo.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    //查找用户个人信息
    public UserInfo getUserInfo(String loginName){
        return userInfoMapper.selectByPrimaryKey(loginName);
    }

    //保存用户修改后的信息
    public int saveBasicSettings(UserInfo userInfo) {
        return userInfoMapper.updateByPrimaryKey(userInfo);
    }

    //保存用户修改后的密码
    public String savePasswordSettings(String loginName, Password password) {
        UserInfo userInfo = userInfoMapper.selectByLogin(loginName, password.getOldPassword());
        if (userInfo != null){
            userInfoMapper.updateLoginPassword(loginName,password.getNewPassword());
            return "true";
        }else {
            return "false";
        }
    }
}
