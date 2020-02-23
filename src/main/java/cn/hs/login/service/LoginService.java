package cn.hs.login.service;

import cn.hs.login.mapper.LoginMapper;
import cn.hs.userinfo.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;

    public UserInfo doLogin(String loginName, String loginPassword) {
        return loginMapper.doLogin(loginName, loginPassword);
    }
}
