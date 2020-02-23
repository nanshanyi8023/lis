package cn.hs.login.service;

import cn.hs.login.mapper.LoginMapper;
import cn.hs.userinfo.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;

    public UserInfo doLogin(String loginName, String loginPassword ,HttpServletResponse response) {
        UserInfo userInfo = loginMapper.doLogin(loginName, loginPassword);
        if (userInfo != null){
            addCookie(response,userInfo);
        }
        return userInfo;
    }

    //将用户名和医院编码添加到cookie
    private void addCookie(HttpServletResponse response,UserInfo userInfo){
        Cookie loginNameCookie = new Cookie("loginName",userInfo.getLoginName());
        loginNameCookie.setPath("/");
        response.addCookie(loginNameCookie);
        Cookie hosnumCookie = new Cookie("hosnum",userInfo.getHosnum());
        hosnumCookie.setPath("/");
        response.addCookie(hosnumCookie);
    }
}
