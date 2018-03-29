package com.libei.dao.mysql;

import com.libei.domain.entity.LbMeetingRoomReserveRecord;
import com.libei.domain.result.MeetingRoomRecordListResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("lbMeetingRoomReserveRecordDao")
public interface LbMeetingRoomReserveRecordDao {
    int deleteById(Integer id);

    int insert(LbMeetingRoomReserveRecord record);

    int insertSelective(LbMeetingRoomReserveRecord record);

    LbMeetingRoomReserveRecord findOne(Integer id);

    int updateByIdSelective(LbMeetingRoomReserveRecord record);

    int updateById(LbMeetingRoomReserveRecord record);

    @Select("select count(1) from lb_meeting_room_reserve_record where room_id = #{roomId} and status = 2")
    Integer findCurrentReservedNumByRoomId(@Param("roomId") Integer roomId);


    @Select("select * from lb_meeting_room_reserve_record where room_id = #{roomId} and status = 2 and (" +
            "(reserve_start_time >= #{reserveStartTimeDesc} && reserve_start_time <= #{reserveEndTimeDesc}) " +
            "|| (reserve_end_time >= #{reserveStartTimeDesc} && reserve_end_time <= #{reserveEndTimeDesc}) " +
            "|| (reserve_start_time < #{reserveStartTimeDesc} && reserve_end_time > #{reserveEndTimeDesc}) " +
            ")")
    List<LbMeetingRoomReserveRecord> findByReserveStartTimeDescAndReserveEndTimeDesc(@Param("roomId") Integer roomId,@Param("reserveStartTimeDesc") Long reserveStartTimeDesc,@Param("reserveEndTimeDesc") Long reserveEndTimeDesc);

    @Select("select * from lb_meeting_room_reserve_record a left join lb_meeting_room b on b.room_id = a.room_id left join lb_user_info c on c.user_id = a.user_id order by a.id desc")
    List<MeetingRoomRecordListResult> findRecordList(@Param("roomId") Integer roomId);

    @Update("update lb_meeting_room_reserve_record set status = #{status} where id = #{id}")
    void updateStatusById(@Param("status") int status, @Param("id") Integer id);
}