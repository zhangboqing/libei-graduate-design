package com.libei.domain.result;

import lombok.Data;

/**
 * @author zhangboqing
 * @date 2018/3/18
 */
@Data
public class MeetingRoomRecordListResult {

    private Integer id;

    private Long reserveStartTime;

    private String reserveStartTimeDesc;

    private Long reserveEndTime;

    private String reserveEndTimeDesc;

    private Integer inNum;

    private Integer status;

    private String statusDesc;


    private Integer roomId;

    private Integer roomNo;



    private Integer userId;

    private String stuName;

    private String stuNo;

    private String phone;

    private String age;

    private Integer sex;

    private String classInfo;


}
