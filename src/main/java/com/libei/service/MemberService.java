package com.libei.service;

import com.libei.domain.entity.LbMember;
import com.libei.domain.form.MemberAddForm;
import com.libei.domain.form.MemberUpdateForm;

import java.util.List;

/**
 * @author zhangboqing
 * @date 2018/1/1
 */
public interface MemberService {
    List<LbMember> findList();

    void addMember(MemberAddForm form);

    void deleteMember(Integer memberId);

    LbMember memberEdit(Integer memberId);

    void memberUpdate(MemberUpdateForm form);
}
