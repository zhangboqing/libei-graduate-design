package com.libei.service.impl;

import com.libei.dao.mysql.LbLoginUserDao;
import com.libei.domain.entity.LbLoginUser;
import com.libei.service.Demo2Service;
import com.libei.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangboqing
 * @date 2018/3/29
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private LbLoginUserDao lbLoginUserDao;
    @Autowired
    private Demo2Service demo2Service;


    @Override
    public String test() {

        LbLoginUser lbLoginUser = new LbLoginUser();
        lbLoginUser.setUserName("test1");
        lbLoginUser.setPassword("123");
        lbLoginUserDao.insertSelective(lbLoginUser);


        test2();
        return "ok";
    }


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
