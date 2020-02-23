package cn.hs.userinfo.controller;

import cn.hs.userinfo.pojo.UserInfo;
import cn.hs.userinfo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/saveBasicSettings", method = RequestMethod.POST)
    public String saveBasicSettings(@RequestBody UserInfo userInfo){
        try {
            return userInfoService.saveBasicSettings(userInfo);
        } catch (Exception e){
            return e.getMessage();
        }
    }
}
