package com.libei.controller;

import com.libei.dao.mysql.LbLoginUserDao;
import com.libei.domain.entity.LbLoginUser;
import com.libei.domain.form.UserLoginForm;
import com.libei.domain.form.UserRegisterForm;
import com.libei.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author zhangboqing
 * @date 2018/1/1
 * 用户登录注册
 */
@Controller
@RequestMapping("/user")
@Api("LbUserController | 用户登录注册相关接口")
public class LbUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private LbLoginUserDao lbLoginUserDao;


    /**
     * 登录
     *
     * @param request
     * @param form
     * @return
     * @throws Exception
     */


    @RequestMapping(value = "/login")
    @ResponseBody
    @ApiOperation(httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "用户登录接口", response = String.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userName", value = "用户名",paramType="form", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
//    })
    public String userLogin(HttpServletRequest request,@ModelAttribute UserLoginForm form) throws Exception {

        LbLoginUser loginUser = lbLoginUserDao.getUserByUsernameAndPassword(form.getUserName(), form.getPassword());

        String message = "success";
        if (Objects.isNull(loginUser)) {
            message = "false";
        }

        return message;
    }

    /**
     * 退出
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout")
    public String userLogout(HttpServletRequest request) throws Exception {

        return "loginAndRegister";
    }


    /**
     * 注册
     *
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public String userRegister(HttpServletRequest request, UserRegisterForm form) throws Exception {

        userService.userRegister(form);
        return "success";
    }
}
