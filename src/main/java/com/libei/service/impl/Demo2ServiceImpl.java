package com.libei.service.impl;

import com.libei.dao.mysql.LbLoginUserDao;
import com.libei.domain.entity.LbLoginUser;
import com.libei.service.Demo2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangboqing
 * @date 2018/3/29
 */
@Service
public class Demo2ServiceImpl implements Demo2Service {

    @Autowired
    private LbLoginUserDao lbLoginUserDao;


    @Override
    @Transactional(rollbackFor = {Throwable.class})
    public String test2() {

        LbLoginUser lbLoginUser = new LbLoginUser();
        lbLoginUser.setUserName("test2");
        lbLoginUser.setPassword("1234");

        lbLoginUserDao.insertSelective(lbLoginUser);

        if (1==1) {
            throw new RuntimeException("测试");
        }
        return "ok";
    }
}
