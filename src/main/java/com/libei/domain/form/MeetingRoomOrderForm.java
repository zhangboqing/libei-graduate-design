package com.libei.domain.form;

import lombok.Data;

/**
 * @author zhangboqing
 * @date 2018/3/18
 */
@Data
public class MeetingRoomOrderForm {
    private Integer userId;
    private Integer roomId;
    private Integer num;
    private String  reserveStartTimeDesc;
    private String  reserveEndTimeDesc;

}
