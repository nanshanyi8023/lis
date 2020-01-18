package cn.hs.login.service;

import cn.hs.login.dto.User_Info;
import cn.hs.login.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;

    public String doLogin(String loginName, String loginPassword) {
        User_Info user_info = loginMapper.doLogin(loginName, loginPassword);
        if (user_info != null){
            return "success";
        }else {
            return "failure";
        }
    }
}
