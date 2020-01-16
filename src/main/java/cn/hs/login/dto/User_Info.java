package cn.hs.login.dto;

import java.io.Serializable;

public class User_Info implements Serializable {
    private String login_name;
    private String login_password;

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }
}
