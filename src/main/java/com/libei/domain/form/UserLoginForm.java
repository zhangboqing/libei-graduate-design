package com.libei.domain.form;

/**
 * @author zhangboqing
 * @date 2018/1/1
 */
public class UserLoginForm {

    /** 登录用户名*/
    private String userName;
    /** 登录密码 */
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginForm{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
