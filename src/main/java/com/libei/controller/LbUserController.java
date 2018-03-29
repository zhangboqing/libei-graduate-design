package com.libei.controller;

import com.libei.constant.BusinessConstant;
import com.libei.dao.mysql.LbLoginUserDao;
import com.libei.dao.mysql.LbUserInfoDao;
import com.libei.domain.entity.LbLoginUser;
import com.libei.domain.entity.LbUserInfo;
import com.libei.domain.form.UserInfoModifyForm;
import com.libei.domain.form.UserLoginForm;
import com.libei.domain.form.UserRegisterForm;
import com.libei.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    @Autowired
    private LbUserInfoDao lbUserInfoDao;


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
    public String userLogin(HttpServletRequest request, HttpSession session, @ModelAttribute UserLoginForm form) throws Exception {

        LbLoginUser loginUser = lbLoginUserDao.getUserByUsernameAndPassword(form.getUserName(), form.getPassword());

//        flag 0 登录失败 1 普通用户 2 管理员用户
        String flag = "0";
        if (Objects.isNull(loginUser)) {
            flag = "0";
        } else {
            Integer type = loginUser.getType();
            if (type.equals(0)) {
                flag = "1";
            } else {
                flag = "2";
            }

//            登录成功
            session.setAttribute(BusinessConstant.USER_LOGIN_KEY,loginUser);
        }

        return flag;
    }

    /**
     * 退出
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout")
    public String userLogout(HttpServletRequest request, HttpSession session,HttpServletResponse response) throws Exception {

        session.removeAttribute(BusinessConstant.USER_LOGIN_KEY);
        response.sendRedirect("/libei/");

//        return "loginAndRegister";
        return null;
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

        //
        LbLoginUser lbLoginUser =  lbLoginUserDao.findByUserName(form.getUserName());

        if (Objects.nonNull(lbLoginUser)) {
            return "1";
        }

        userService.userRegister(form);



        return "success";
    }


    /**
     * 密码修改
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/password/modify")
    @ResponseBody
    public String userPasswordModify(HttpServletRequest request,HttpSession session, String newPassword) throws Exception {

        //获取当前用户信息
        LbLoginUser loginUser = (LbLoginUser)session.getAttribute(BusinessConstant.USER_LOGIN_KEY);
        loginUser.setPassword(newPassword);

        lbLoginUserDao.updateByIdSelective(loginUser);

        return "success";
    }


    /**
     * 跳转修改个人信息页面
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/modify/info/page")
    public String toUserModifyInfoPage(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        //获取当前用户信息
        LbLoginUser loginUser = (LbLoginUser)session.getAttribute(BusinessConstant.USER_LOGIN_KEY);
        loginUser = lbLoginUserDao.findOne(loginUser.getUserId());
        LbUserInfo userInfo = lbUserInfoDao.findByUserId(loginUser.getUserId());

        model.addAttribute("userInfo",userInfo);

        return "modifyUserInfo";
    }


    /**
     * 修改个人信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info/modify")
    @ResponseBody
    public String userInfoModify(HttpServletRequest request, HttpSession session, Model model, UserInfoModifyForm form) throws Exception {
        //获取当前用户信息
        LbLoginUser loginUser = (LbLoginUser)session.getAttribute(BusinessConstant.USER_LOGIN_KEY);
        loginUser = lbLoginUserDao.findOne(loginUser.getUserId());

        LbUserInfo userInfo = lbUserInfoDao.findByUserId(loginUser.getUserId());
        if (Objects.isNull(userInfo)) {
            userInfo = new LbUserInfo();
            userInfo.setUserId(loginUser.getUserId());
            BeanUtils.copyProperties(form,userInfo);

            userInfo.setStuName(form.getRealName());
            lbUserInfoDao.insertSelective(userInfo);
        } else {
            BeanUtils.copyProperties(form,userInfo);
            userInfo.setStuName(form.getRealName());
            lbUserInfoDao.updateByIdSelective(userInfo);
        }

        return "success";
    }
}
