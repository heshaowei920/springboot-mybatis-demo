package com.winter.model;

/**
 * @author ：heshaowei
 * @date ：Created in 2019/9/25 16:00
 * @description：登录验证token
 * @modified By：
 * @version: v1.0
 */
public class LoginToken {
    private Integer userId;
    private String password;

    public LoginToken(){}
    public LoginToken(Integer userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
