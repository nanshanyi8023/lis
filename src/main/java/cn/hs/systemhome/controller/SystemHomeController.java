package cn.hs.systemhome.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/systemHome")
public class SystemHomeController {

    @RequestMapping(value = "/getView.htm",method = RequestMethod.GET)
    public ModelAndView getView(ModelMap modelMap){
        return new ModelAndView("systemhome/systemHome.jsp",modelMap);
    }


    @RequestMapping(value = "/signOut.htm",method = RequestMethod.GET)
    public ModelAndView getViewSignOut(ModelMap modelMap){
        return new ModelAndView("login/login.jsp",modelMap);
    }
}
