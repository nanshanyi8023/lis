package cn.hs.login.controller;

import cn.hs.ApiResult;
import cn.hs.login.service.LoginService;
import cn.hs.userinfo.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/getView", method = RequestMethod.GET)
    public ModelAndView getView(ModelMap modelMap) {  //Bas_hospitals hospital,
        return new ModelAndView("login/login.jsp", modelMap);
    }

    //执行登录操作
    @RequestMapping(value = "/doLogin.json", method = RequestMethod.GET)
    public ApiResult doLogin(@RequestParam(required = true) String loginName , @RequestParam(required = true) String loginPassword){
        try {
            UserInfo userInfo = loginService.doLogin(loginName, loginPassword);
            return ApiResult.success(userInfo);
        } catch (Exception e){
            return ApiResult.failed(e.getMessage());
        }
    }

}
