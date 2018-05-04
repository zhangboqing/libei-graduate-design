package com.libei.controller;

import com.libei.common.utils.JodaTimeUtils;
import com.libei.constant.BusinessConstant;
import com.libei.dao.mysql.LbLoginUserDao;
import com.libei.dao.mysql.LbMeetingRoomDao;
import com.libei.dao.mysql.LbMeetingRoomReserveRecordDao;
import com.libei.domain.entity.LbLoginUser;
import com.libei.domain.entity.LbMeetingRoom;
import com.libei.domain.entity.LbMeetingRoomReserveRecord;
import com.libei.domain.form.MeetingRoomAddForm;
import com.libei.domain.form.MeetingRoomOrderForm;
import com.libei.domain.form.MeetingRoomUpdateForm;
import com.libei.domain.result.LoginUserInfoResult;
import com.libei.domain.result.MeetingRoomListResult;
import com.libei.domain.result.MeetingRoomRecordListResult;
import com.libei.service.MeetingRoomService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

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
    private LbLoginUserDao lbLoginUserDao;
    @Autowired
    private LbMeetingRoomReserveRecordDao lbMeetingRoomReserveRecordDao;


    /**
     * 会议室列表
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public String meetingRoomList(Model model) throws Exception {

        List<MeetingRoomListResult> meetingRoomList = meetingRoomService.findList();
        model.addAttribute("meetingRoomList", meetingRoomList);
        return "meetingRoomList";
    }


    /**
     * 会议室列表
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list2")
    public String meetingRoomList2(Model model) throws Exception {

        List<MeetingRoomListResult> meetingRoomList = meetingRoomService.findList();
        model.addAttribute("meetingRoomList", meetingRoomList);
        return "meetingRoomList2";
    }

    /**
     * 添加会议室
     *
     * @param form
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public String addMeetingRoom(MeetingRoomAddForm form) throws Exception {

        //校验房间号是否存在
        LbMeetingRoom byRoomNo = lbMeetingRoomDao.findByRoomNo(form.getRoomNo());
        if (Objects.nonNull(byRoomNo)) {
            return "1";
        }

        meetingRoomService.addMeetingRoom(form);
        return "0";
    }


    /**
     * 删除成员
     *
     * @param roomId
     * @param model
     * @throws Exception
     */
    @RequestMapping(value = "/delete")
    public String deleteMeetingRoom(Integer roomId, Model model) throws Exception {


        meetingRoomService.deleteMeetingRoom(roomId);
        //删除会议室关联的记录
        lbMeetingRoomReserveRecordDao.deleteByRoomId(roomId);

        return meetingRoomList(model);
    }


    /**
     * 编辑成员
     *
     * @param roomId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit")
    public String meetingRoomEdit(Integer roomId, Model model) throws Exception {

        LbMeetingRoom meetingRoom = meetingRoomService.meetingRoomEdit(roomId);
        model.addAttribute("meetingRoom", meetingRoom);
        return "editMeetingRoom";
    }


    /**
     * 成员更新
     *
     * @param form
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public String meetingRoomUpdate(MeetingRoomUpdateForm form, Model model) throws Exception {

        meetingRoomService.meetingRoomUpdate(form);
        return "success";
    }

    /**
     * 会议室预定记录列表 - 管理员
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/viewOrderRecordList")
    public String meetingRoomRecordList(Integer roomId, Model model) throws Exception {

        List<MeetingRoomRecordListResult> recordList = meetingRoomService.findRecordList(roomId);
        model.addAttribute("recordList", recordList);
        return "meetingRoomRecordList";
    }


    /**
     * 审核不通过
     *
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkNotPass")
    public String checkNotPass(Integer id, Integer roomId, Model model) throws Exception {

        lbMeetingRoomReserveRecordDao.updateStatusById(1,id);

        return meetingRoomRecordList(roomId, model);
    }

    /**
     * 审核通过
     *
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkPass")
    public String checkPass(Integer id, Integer roomId, Model model) throws Exception {

        lbMeetingRoomReserveRecordDao.updateStatusById(2,id);

        return meetingRoomRecordList(roomId, model);
    }

    /**
     * 会议室预定记录列表 - 用户
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/viewOrderRecordList2")
    public String meetingRoomRecordList2(Integer roomId, Model model,HttpSession session) throws Exception {

        //获取当前用户信息
        LbLoginUser loginUser = (LbLoginUser) session.getAttribute(BusinessConstant.USER_LOGIN_KEY);

        List<MeetingRoomRecordListResult> recordList = meetingRoomService.meetingRoomRecordList2(loginUser.getUserId());
        model.addAttribute("recordList", recordList);
        return "meetingRoomRecordList2";
    }

    /**
     * 会议室预定页面 - 管理员
     *
     * @param roomId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/orderPage")
    public String meetingRoomOrderPage(Integer roomId, Model model) throws Exception {

        List<LoginUserInfoResult> memberList = lbLoginUserDao.findList();
        LbMeetingRoom meetingRoom = lbMeetingRoomDao.findOne(roomId);

        model.addAttribute("memberList", memberList);
        model.addAttribute("meetingRoom", meetingRoom);

        return "orderMeetingRoomPage";
    }

    /**
     * 会议室预定页面 - 用户
     *
     * @param roomId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/orderPage2")
    public String meetingRoomOrderPage2(Integer roomId, Model model, HttpSession session) throws Exception {

        //获取当前用户信息
        LbLoginUser loginUser = (LbLoginUser) session.getAttribute(BusinessConstant.USER_LOGIN_KEY);

        LbMeetingRoom meetingRoom = lbMeetingRoomDao.findOne(roomId);

        model.addAttribute("userId", loginUser.getUserId());
        model.addAttribute("meetingRoom", meetingRoom);

        return "orderMeetingRoomPage2";
    }

    /**
     * 预定 - 管理员
     *
     * @param form
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/order")
    @ResponseBody
    public String meetingRoomOrder(MeetingRoomOrderForm form, Model model) throws Exception {

        String flag = checkMeetingRoomOrder(form.getRoomId(), form.getNum(), form.getReserveStartTimeDesc(), form.getReserveEndTimeDesc());

        if (!flag.equals("0")) {
            return flag;
        }

        //存储记录
        LbMeetingRoomReserveRecord record = new LbMeetingRoomReserveRecord();
        record.setRoomId(form.getRoomId());
        record.setUserId(form.getUserId());
        record.setReserveStartTime(JodaTimeUtils.toTimeStamp(form.getReserveStartTimeDesc(), JodaTimeUtils.DateFormat.DATETIME_FORMAT));
        record.setReserveEndTime(JodaTimeUtils.toTimeStamp(form.getReserveEndTimeDesc(), JodaTimeUtils.DateFormat.DATETIME_FORMAT));
        record.setStatus(2);
        record.setInNum(form.getNum());

        lbMeetingRoomReserveRecordDao.insertSelective(record);
        return "0";
    }


    /**
     * 预定 - 用户
     *
     * @param form
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/order2")
    @ResponseBody
    public String meetingRoomOrder2(MeetingRoomOrderForm form, Model model) throws Exception {

        String flag = checkMeetingRoomOrder(form.getRoomId(), form.getNum(), form.getReserveStartTimeDesc(), form.getReserveEndTimeDesc());

        if (!flag.equals("0")) {
            return flag;
        }

        //存储记录
        LbMeetingRoomReserveRecord record = new LbMeetingRoomReserveRecord();
        record.setRoomId(form.getRoomId());
        record.setUserId(form.getUserId());
        record.setReserveStartTime(JodaTimeUtils.toTimeStamp(form.getReserveStartTimeDesc(), JodaTimeUtils.DateFormat.DATETIME_FORMAT));
        record.setReserveEndTime(JodaTimeUtils.toTimeStamp(form.getReserveEndTimeDesc(), JodaTimeUtils.DateFormat.DATETIME_FORMAT));
        record.setStatus(0);
        record.setInNum(form.getNum());

        lbMeetingRoomReserveRecordDao.insertSelective(record);
        return "0";
    }

    /**
     * 校验会议室是否被预定的方法
     *
     * @param roomId
     * @param num
     * @param reserveStartTimeDesc
     * @param reserveEndTimeDesc
     * @return "1" 数量太大  "2" 当前房间在该时间范围内已被预订
     */
    private String checkMeetingRoomOrder(Integer roomId, Integer num, String reserveStartTimeDesc, String reserveEndTimeDesc) {
        //校验人数
        LbMeetingRoom meetingRoom = lbMeetingRoomDao.findOne(roomId);
        if (meetingRoom.getRoomCanInNumber().compareTo(num) < 0) {
            return "1";
        }

        Long reserveStartTime = JodaTimeUtils.toTimeStamp(reserveStartTimeDesc, JodaTimeUtils.DateFormat.DATETIME_FORMAT);
        Long reserveEndTime = JodaTimeUtils.toTimeStamp(reserveEndTimeDesc, JodaTimeUtils.DateFormat.DATETIME_FORMAT);

        //校验时间
        List<LbMeetingRoomReserveRecord> recordList = lbMeetingRoomReserveRecordDao.findByReserveStartTimeDescAndReserveEndTimeDesc(roomId, reserveStartTime, reserveEndTime);

        if (CollectionUtils.isNotEmpty(recordList) && recordList.size() > 0) {
            return "2";
        }

        return "0";
    }
}
