package com.libei.controller;

import com.libei.dao.mysql.LbMeetingRoomDao;
import com.libei.dao.mysql.LbMemberDao;
import com.libei.domain.entity.LbMeetingRoom;
import com.libei.domain.entity.LbMember;
import com.libei.domain.form.MeetingRoomAddForm;
import com.libei.domain.form.MeetingRoomUpdateForm;
import com.libei.domain.result.MeetingRoomListResult;
import com.libei.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhangboqing
 * @date 2018/1/1
 * 会议室管理
 */
@Controller
@RequestMapping("/meeting/room")
public class LbMeetingRoomController {

    @Autowired
    private MeetingRoomService meetingRoomService;
    @Autowired
    private LbMeetingRoomDao lbMeetingRoomDao;
    @Autowired
    private LbMemberDao lbMemberDao;


    /**
     * 会议室列表
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public String meetingRoomList(Model model) throws Exception {

        List<MeetingRoomListResult> meetingRoomList = meetingRoomService.findList();
        model.addAttribute("meetingRoomList",meetingRoomList);
        return "meetingRoomList";
    }


    /**
     * 添加成员
     * @param form
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public String addMeetingRoom(MeetingRoomAddForm form) throws Exception {

        meetingRoomService.addMeetingRoom(form);
        return "success";
    }


    /**
     * 删除成员
     * @param id
     * @param model
     * @throws Exception
     */
    @RequestMapping(value = "/delete")
    public String  deleteMeetingRoom(Integer id,Model model) throws Exception {

        meetingRoomService.deleteMeetingRoom(id);
        return meetingRoomList(model);
    }


    /**
     * 编辑成员
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit")
    public String  meetingRoomEdit(Integer id,Model model) throws Exception {

        LbMeetingRoom meetingRoom = meetingRoomService.meetingRoomEdit(id);
        model.addAttribute("meetingRoom",meetingRoom);
        return "editMeetingRoom";
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
    public String  meetingRoomUpdate(MeetingRoomUpdateForm form, Model model) throws Exception {

        meetingRoomService.meetingRoomUpdate(form);
        return "success";
    }

    /**
     * 会议室预定页面
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/orderPage")
    public String  meetingRoomOrderPage(Integer id, Model model) throws Exception {

        List<LbMember> memberList = lbMemberDao.findList();
        LbMeetingRoom meetingRoom = lbMeetingRoomDao.findOne(id);

        model.addAttribute("memberList",memberList);
        model.addAttribute("meetingRoom",meetingRoom);

        return "orderMeetingRoomPage";
    }

    /**
     * 预定
     * @param id
     * @param memberId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/order")
    @ResponseBody
    public String  meetingRoomOrder(Integer id,Integer memberId, Model model) throws Exception {

        meetingRoomService.meetingRoomOrder(id,memberId);
        return "success";
    }
}
