package com.libei.controller;

import com.libei.domain.entity.LbMember;
import com.libei.domain.form.MemberAddForm;
import com.libei.domain.form.MemberUpdateForm;
import com.libei.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class LbMemberController {

    @Autowired
    private MemberService memberService;


    /**
     * 成员列表
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public String memberList(Model model) throws Exception {

        List<LbMember> memberList = memberService.findList();
        model.addAttribute("memberList",memberList);
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

        memberService.addMember(form);
        return "success";
    }


    /**
     * 删除成员
     * @param memberId
     * @param model
     * @throws Exception
     */
    @RequestMapping(value = "/delete")
    public String  deleteMember(Integer memberId,Model model) throws Exception {

        memberService.deleteMember(memberId);
        return memberList(model);
    }


    /**
     * 编辑成员
     * @param memberId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit")
    public String  memberEdit(Integer memberId,Model model) throws Exception {

        LbMember member = memberService.memberEdit(memberId);
        model.addAttribute("member",member);
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

        memberService.memberUpdate(form);
        return "success";
    }
}
