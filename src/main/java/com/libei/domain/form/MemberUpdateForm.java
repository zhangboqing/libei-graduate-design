package com.libei.domain.form;

import lombok.Data;

/**
 * @author zhangboqing
 * @date 2018/1/27
 */
@Data
public class MemberUpdateForm {

    private Integer userId;

    private String age;

    private Integer sex;

    private String classInfo;

    private String stuNo;

    private String realName;

    private String phone;
}
