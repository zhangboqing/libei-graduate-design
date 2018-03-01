package com.libei.dao.mysql;

import com.libei.domain.entity.LbMeetingRoom;
import com.libei.domain.result.MeetingRoomListResult;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("lbMeetingRoomDao")
public interface LbMeetingRoomDao {
    int deleteById(Integer id);

    int insert(LbMeetingRoom record);

    int insertSelective(LbMeetingRoom record);

    LbMeetingRoom findOne(Integer id);

    int updateByIdSelective(LbMeetingRoom record);

    int updateById(LbMeetingRoom record);

    @Select("select * from lb_meeting_room room " +
            "left join lb_member member on member.member_id = room.member_id")
    List<MeetingRoomListResult> findList();

}