package com.libei.service;

import com.libei.domain.form.UserLoginForm;
import com.libei.domain.form.UserRegisterForm;

/**
 * @author zhangboqing
 * @date 2018/1/1
 */
public interface UserService {

    void userLogin(UserLoginForm form);

    void userRegister(UserRegisterForm form);
}
