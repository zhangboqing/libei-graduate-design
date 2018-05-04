package com.libei.service.impl;

import com.libei.common.utils.JodaTimeUtils;
import com.libei.constant.BusinessConstant;
import com.libei.dao.mysql.LbMeetingRoomDao;
import com.libei.dao.mysql.LbMeetingRoomReserveRecordDao;
import com.libei.domain.entity.LbMeetingRoom;
import com.libei.domain.form.MeetingRoomAddForm;
import com.libei.domain.form.MeetingRoomUpdateForm;
import com.libei.domain.result.MeetingRoomListResult;
import com.libei.domain.result.MeetingRoomRecordListResult;
import com.libei.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author zhangboqing
 * @date 2018/1/1
 */
@Service("meetingRoomService")
public class MeetingRoomServiceImpl implements MeetingRoomService {

    @Autowired
    private LbMeetingRoomDao lbMeetingRoomDao;
    @Autowired
    private LbMeetingRoomReserveRecordDao lbMeetingRoomReserveRecordDao;

    @Override
    public List<MeetingRoomListResult> findList() {
        List<MeetingRoomListResult> list = lbMeetingRoomDao.findList();

        if (CollectionUtils.isEmpty(list)) {
            return list;
        }

        for (MeetingRoomListResult room : list) {

            //查询该房间预定人数
            Integer currentReservedNum = lbMeetingRoomReserveRecordDao.findCurrentReservedNumByRoomId(room.getRoomId());
            room.setCurrentReservedNum(currentReservedNum);
        }


        return list;
    }

    @Override
    public void addMeetingRoom(MeetingRoomAddForm form) {
        LbMeetingRoom meetingRoom = new LbMeetingRoom();
        meetingRoom.setRoomNo(form.getRoomNo());
        meetingRoom.setRoomCanInNumber(form.getRoomCanInNumber());
        meetingRoom.setRoomFacility(form.getRoomFacility());

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
        LbMeetingRoom one = lbMeetingRoomDao.findOne(form.getRoomId());
        one.setRoomCanInNumber(form.getRoomCanInNumber());
        one.setRoomFacility(form.getRoomFacility());

        lbMeetingRoomDao.updateByIdSelective(one);
    }

    @Override
    public List<MeetingRoomRecordListResult> findRecordList(Integer roomId) {

        List<MeetingRoomRecordListResult> resultList = lbMeetingRoomReserveRecordDao.findRecordList(roomId);
        if (!CollectionUtils.isEmpty(resultList)) {
            for (MeetingRoomRecordListResult result : resultList) {
                result.setStatusDesc(BusinessConstant.MeetingRoome.transforStatusValue(result.getStatus()));
                result.setReserveStartTimeDesc(JodaTimeUtils.timestampToString(result.getReserveStartTime(), JodaTimeUtils.DateFormat.DATETIME_FORMAT));
                result.setReserveEndTimeDesc(JodaTimeUtils.timestampToString(result.getReserveEndTime(), JodaTimeUtils.DateFormat.DATETIME_FORMAT));
            }
        }
        return resultList;
    }

    @Override
    public List<MeetingRoomRecordListResult> meetingRoomRecordList2(Integer userId) {
        List<MeetingRoomRecordListResult> resultList = lbMeetingRoomReserveRecordDao.findRecordList2(userId);
        if (!CollectionUtils.isEmpty(resultList)) {
            for (MeetingRoomRecordListResult result : resultList) {
                result.setStatusDesc(BusinessConstant.MeetingRoome.transforStatusValue(result.getStatus()));
                result.setReserveStartTimeDesc(JodaTimeUtils.timestampToString(result.getReserveStartTime(), JodaTimeUtils.DateFormat.DATETIME_FORMAT));
                result.setReserveEndTimeDesc(JodaTimeUtils.timestampToString(result.getReserveEndTime(), JodaTimeUtils.DateFormat.DATETIME_FORMAT));
            }
        }
        return resultList;
    }


}
