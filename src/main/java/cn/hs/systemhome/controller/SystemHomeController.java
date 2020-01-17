package cn.hs.systemhome.controller;

import cn.hs.systemhome.service.SystemHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/systemHome")
public class SystemHomeController {
    @Autowired
    private SystemHomeService systemHomeService;

    @RequestMapping(value = "/getView.htm",method = RequestMethod.GET)
    public ModelAndView getView(ModelMap modelMap){
        return new ModelAndView("systemhome/systemHome.jsp",modelMap);
    }


}
