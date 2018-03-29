package com.libei.service.impl;

import com.libei.dao.mysql.LbLoginUserDao;
import com.libei.dao.mysql.LbUserInfoDao;
import com.libei.domain.entity.LbLoginUser;
import com.libei.domain.entity.LbUserInfo;
import com.libei.domain.form.UserLoginForm;
import com.libei.domain.form.UserRegisterForm;
import com.libei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangboqing
 * @date 2018/1/1
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private LbLoginUserDao lbLoginUserDao;
    @Autowired
    private LbUserInfoDao lbUserInfoDao;


    @Override
    public void userLogin(UserLoginForm form) {

        LbLoginUser  lbLoginUser = lbLoginUserDao.getUserByUsernameAndPassword(form.getUserName(),form.getPassword());

        System.out.println(lbLoginUser);

    }

    @Override
    public void userRegister(UserRegisterForm form) {

        LbLoginUser lbLoginUser = new LbLoginUser();
        lbLoginUser.setUserName(form.getUserName());
        lbLoginUser.setPassword(form.getPassword());

        lbLoginUserDao.insertSelective(lbLoginUser);

        LbUserInfo userInfo = new LbUserInfo();
        userInfo.setUserId(lbLoginUser.getUserId());
        lbUserInfoDao.insertSelective(userInfo);
    }
}
