package com.libei.domain.result;

import lombok.Data;

/**
 * @author zhangboqing
 * @date 2018/1/27
 */
@Data
public class MeetingRoomListResult {
    private Integer id;

    private Integer roomNo;

    private Integer isReserve;

    private String isReserveDesc;

    private Integer memberId;

    private String name;
    private String phone;
    private String identity;

    private String memberDesc;
}
