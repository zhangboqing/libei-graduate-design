package com.libei.domain.form;

import lombok.Data;

/**
 * @author zhangboqing
 * @date 2018/1/27
 */
@Data
public class MeetingRoomUpdateForm {
    private Integer roomId;

    private Integer roomCanInNumber;

    private String roomFacility;
}
