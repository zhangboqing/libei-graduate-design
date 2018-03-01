package com.libei.domain.form;

import lombok.Data;

/**
 * @author zhangboqing
 * @date 2018/1/27
 */
@Data
public class MemberUpdateForm {

    private Integer memberId;

    private String name;

    private String phone;

    private String identity;
}
