package cn.hs.userinfo.dto;

import lombok.Data;

@Data
public class Password {
    private String oldPassword;
    private String newPassword;
}
