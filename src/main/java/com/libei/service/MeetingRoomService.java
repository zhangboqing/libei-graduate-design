package com.libei.service;

import com.libei.domain.entity.LbMeetingRoom;
import com.libei.domain.form.MeetingRoomAddForm;
import com.libei.domain.form.MeetingRoomUpdateForm;
import com.libei.domain.result.MeetingRoomListResult;

import java.util.List;

/**
 * @author zhangboqing
 * @date 2018/1/1
 */
public interface MeetingRoomService {
    List<MeetingRoomListResult> findList();

    void addMeetingRoom(MeetingRoomAddForm form);

    void deleteMeetingRoom(Integer id);

    LbMeetingRoom meetingRoomEdit(Integer id);


    void meetingRoomUpdate(MeetingRoomUpdateForm form);

    void meetingRoomOrder(Integer id, Integer memberId);
}
