package com.libei.domain.form;

import lombok.Data;

/**
 * @author zhangboqing
 * @date 2018/1/1
 */
@Data
public class UserLoginForm {

    /** 登录用户名*/
    private String userName;
    /** 登录密码 */
    private String password;

}
