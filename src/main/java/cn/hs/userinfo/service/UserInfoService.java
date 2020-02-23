package cn.hs.userinfo.service;

import cn.hs.userinfo.mapper.UserInfoMapper;
import cn.hs.userinfo.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public String saveBasicSettings(UserInfo userInfo) {
        return null;
    }
}
