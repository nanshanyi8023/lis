package cn.hs.login.controller;

import cn.hs.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService = null;

    @RequestMapping(value = "/getView.htm", method = RequestMethod.GET)
    public ModelAndView getView(ModelMap modelMap) {  //Bas_hospitals hospital,

        return new ModelAndView("login/login.jsp", modelMap);
    }

}
