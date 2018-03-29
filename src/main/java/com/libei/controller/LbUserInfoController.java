package com.libei.controller;

import com.libei.constant.BusinessConstant;
import com.libei.dao.mysql.LbLoginUserDao;
import com.libei.dao.mysql.LbUserInfoDao;
import com.libei.domain.entity.LbLoginUser;
import com.libei.domain.entity.LbUserInfo;
import com.libei.domain.form.MemberAddForm;
import com.libei.domain.form.MemberUpdateForm;
import com.libei.domain.result.LoginUserInfoResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhangboqing
 * @date 2018/1/1
 * 成员管理
 */
@Controller
@RequestMapping("/member")
public class LbUserInfoController {

    @Autowired
    private LbLoginUserDao lbLoginUserDao;
    @Autowired
    private LbUserInfoDao lbUserInfoDao;

    /**
     * 成员列表
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public String memberList(Model model) throws Exception {

        List<LoginUserInfoResult> userList = lbLoginUserDao.findList();
        if (!CollectionUtils.isEmpty(userList)) {
            for (LoginUserInfoResult userInfo : userList) {
                userInfo.setSexDesc(BusinessConstant.UserInfo.transforSexValue(userInfo.getSex()));
            }
        }


        model.addAttribute("userList",userList);
        return "memberList";
    }


    /**
     * 添加成员
     * @param form
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public String addMember(MemberAddForm form) throws Exception {

        //新增登录用户
        LbLoginUser lbLoginUser = new LbLoginUser();
        lbLoginUser.setUserName(form.getUserName());
        lbLoginUser.setPassword(form.getPassword());
        lbLoginUserDao.insertSelective(lbLoginUser);

        //新增用户信息记录
        LbUserInfo userInfo = new LbUserInfo();
        BeanUtils.copyProperties(form,userInfo);
        userInfo.setStuName(form.getRealName());
        userInfo.setUserId(lbLoginUser.getUserId());

        lbUserInfoDao.insertSelective(userInfo);

        return "success";
    }


    /**
     * 删除成员
     * @param userId
     * @param model
     * @throws Exception
     */
    @RequestMapping(value = "/delete")
    public String  deleteMember(Integer userId,Model model) throws Exception {
        lbLoginUserDao.deleteById(userId);

        lbUserInfoDao.deleteByUserId(userId);

        return memberList(model);
    }


    /**
     * 编辑成员
     * @param userId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit")
    public String  memberEdit(Integer userId,Model model) throws Exception {

        LoginUserInfoResult userInfo = new LoginUserInfoResult();
        LbUserInfo byUserId = lbUserInfoDao.findByUserId(userId);
        BeanUtils.copyProperties(byUserId,userInfo);

        LbLoginUser one = lbLoginUserDao.findOne(userId);
        userInfo.setUserName(one.getUserName());

        model.addAttribute("userInfo",userInfo);
        return "editMember";
    }


    /**
     * 成员更新
     * @param form
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public String  memberUpdate(MemberUpdateForm form, Model model) throws Exception {

        LbUserInfo userInfo = lbUserInfoDao.findByUserId(form.getUserId());
        BeanUtils.copyProperties(form,userInfo);

        lbUserInfoDao.updateByIdSelective(userInfo);
        return "success";
    }
}
