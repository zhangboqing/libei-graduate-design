package com.libei.service.impl;

import com.libei.dao.mysql.LbMeetingRoomDao;
import com.libei.domain.entity.LbMeetingRoom;
import com.libei.domain.form.MeetingRoomAddForm;
import com.libei.domain.form.MeetingRoomUpdateForm;
import com.libei.domain.result.MeetingRoomListResult;
import com.libei.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author zhangboqing
 * @date 2018/1/1
 */
@Service("meetingRoomService")
public class MeetingRoomServiceImpl implements MeetingRoomService {

    @Autowired
    private LbMeetingRoomDao lbMeetingRoomDao;

    @Override
    public List<MeetingRoomListResult> findList() {
        List<MeetingRoomListResult> list = lbMeetingRoomDao.findList();

        if (CollectionUtils.isEmpty(list)) {
            return list;
        }

        for (MeetingRoomListResult meetingRoomListResult : list) {
            Integer isReserve = meetingRoomListResult.getIsReserve();
            if (isReserve.equals(0)) {
                meetingRoomListResult.setIsReserveDesc("空闲");
            } else {
                meetingRoomListResult.setIsReserveDesc("已预定");
            }

            if (!StringUtils.isEmpty(meetingRoomListResult.getName()) &&
                    !StringUtils.isEmpty(meetingRoomListResult.getPhone()) &&
                    !StringUtils.isEmpty(meetingRoomListResult.getIdentity())) {
                meetingRoomListResult.setMemberDesc(meetingRoomListResult.getName()+"-"+meetingRoomListResult.getPhone()
                +"-"+meetingRoomListResult.getId());
            } else {
                meetingRoomListResult.setMemberDesc("");
            }
        }

        return list;
    }

    @Override
    public void addMeetingRoom(MeetingRoomAddForm form) {
        LbMeetingRoom meetingRoom = new LbMeetingRoom();
        meetingRoom.setRoomNo(form.getRoomNo());

        lbMeetingRoomDao.insertSelective(meetingRoom);
    }

    @Override
    public void deleteMeetingRoom(Integer id) {
        lbMeetingRoomDao.deleteById(id);
    }

    @Override
    public LbMeetingRoom meetingRoomEdit(Integer id) {
        return lbMeetingRoomDao.findOne(id);
    }

    @Override
    public void meetingRoomUpdate(MeetingRoomUpdateForm form) {
        LbMeetingRoom one = lbMeetingRoomDao.findOne(form.getId());
        one.setRoomNo(form.getRoomNo());

        lbMeetingRoomDao.updateByIdSelective(one);
    }

    @Override
    public void meetingRoomOrder(Integer id, Integer memberId) {
        LbMeetingRoom one = lbMeetingRoomDao.findOne(id);
        one.setIsReserve(1);
        one.setMemberId(memberId);

        lbMeetingRoomDao.updateByIdSelective(one);
    }

}
