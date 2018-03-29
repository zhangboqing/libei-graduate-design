package com.libei.domain.result;

import lombok.Data;

/**
 * @author zhangboqing
 * @date 2018/1/27
 */
@Data
public class MeetingRoomListResult {
    private Integer roomId;

    private Integer roomNo;

    private Integer roomCanInNumber;

    private String roomFacility;

    //当前预定人数
    private Integer currentReservedNum;

}
