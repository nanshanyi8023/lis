package cn.hs.publicclass.method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 查找Cookie工具类
 */

@Service
public class GetCookieService {
    @Autowired
    private  HttpServletRequest request;

    //查找医院号
    public String getHosNum() {
        Cookie cookies[] = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("hosNum")) {
                return cookie.getValue();
            }
        }
        return null;
    }

    //查找登录用户姓名
    public String getLoginName() {
        Cookie cookies[] = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("loginName")) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
