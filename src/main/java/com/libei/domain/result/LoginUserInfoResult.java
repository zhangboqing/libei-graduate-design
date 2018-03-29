package com.libei.domain.result;

import lombok.Data;

/**
 * @author zhangboqing
 * @date 2018/3/17
 */
@Data
public class LoginUserInfoResult {

    private String userName;

    private String stuName;

    private String stuNo;

    private String phone;

    private String age;

    private Integer sex;

    private String sexDesc;

    private String classInfo;

    private Integer userId;

}
