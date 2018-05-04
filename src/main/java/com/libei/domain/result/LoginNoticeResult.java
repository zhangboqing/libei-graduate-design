package com.libei.domain.result;

import lombok.Data;

import java.util.Date;

/**
 * @author zhangboqing
 * @date 2018/3/17
 */
@Data
public class LoginNoticeResult {

    private Integer id;

    private String content;

    private Date releaseTime;

    private String releaseTimeDesc;



}
