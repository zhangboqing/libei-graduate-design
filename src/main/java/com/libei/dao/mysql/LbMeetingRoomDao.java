package com.libei.dao.mysql;

import com.libei.domain.entity.LbMeetingRoom;
import com.libei.domain.result.MeetingRoomListResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LbMeetingRoomDao {
    int deleteById(Integer roomId);

    int insert(LbMeetingRoom record);

    int insertSelective(LbMeetingRoom record);

    LbMeetingRoom findOne(Integer roomId);

    int updateByIdSelective(LbMeetingRoom record);

    int updateById(LbMeetingRoom record);

    @Select("select * from lb_meeting_room order by room_id desc")
    List<MeetingRoomListResult> findList();

    @Select("select * from lb_meeting_room where room_no = #{roomNo}")
    LbMeetingRoom findByRoomNo(@Param("roomNo") Integer roomNo);
}