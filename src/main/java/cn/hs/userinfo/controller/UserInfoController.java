package cn.hs.userinfo.controller;

import cn.hs.publicmethod.ApiResult;
import cn.hs.userinfo.dto.Password;
import cn.hs.userinfo.pojo.UserInfo;
import cn.hs.userinfo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    private String loginName = "";

    //根据cookie中的用户名和医院编码 查找用户个人信息
    @RequestMapping(value = "/getUserInfo.json", method = RequestMethod.GET)
    public ApiResult getUserInfo(HttpServletRequest request){
        try {
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
                if (cookie.getName().equals("loginName")){
                    loginName = cookie.getValue();
                }
            }
            return ApiResult.success(userInfoService.getUserInfo(loginName));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //保存用户修改后的信息
    @RequestMapping(value = "/saveBasicSettings", method = RequestMethod.POST)
    public ApiResult saveBasicSettings(@RequestBody UserInfo userInfo){
        try {
            userInfo.setLoginName(loginName);
            return ApiResult.success(userInfoService.saveBasicSettings(userInfo));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

    //保存用户修改后的密码
    @RequestMapping(value = "/savePasswordSettings", method = RequestMethod.POST)
    public ApiResult savePasswordSettings(@RequestBody Password password){
        try {
            return ApiResult.success(userInfoService.savePasswordSettings(loginName,password));
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

}
